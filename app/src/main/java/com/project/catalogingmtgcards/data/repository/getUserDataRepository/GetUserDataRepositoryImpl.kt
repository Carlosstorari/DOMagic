package com.project.catalogingmtgcards.data.repository.getUserDataRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.project.catalogingmtgcards.domain.model.DeckItem

class GetUserDataRepositoryImpl(private val firestore: FirebaseFirestore) {

    fun getByDocumentId(): LiveData<List<DeckItem>> = MutableLiveData<List<DeckItem>>().apply {
        firestore.collection("users").addSnapshotListener { s, _ ->
            Firebase.auth.currentUser?.let { user ->
                s?.let { snapshot ->
                    var list: MutableList<DeckItem> = mutableListOf()
                    snapshot
                        .documents
                        .filter { it.id == user.uid }
                        .mapNotNull { doc ->
                            val deckList = doc.get("deckList") as List<Map<String, Any>>
                            deckList.forEach {
                                val name = it["name"] as String
                                val cardList = it["cardList"] as List<String>
                                list.add(DeckItemDocument(name, cardList).toDeckItem(user.uid))
                            }
                        }
                    value = list
                }
            }

        }
    }

    private class DeckItemDocument(
        val name: String = "",
        val listCard: List<String>
    ){
        fun toDeckItem(id: String) = DeckItem(
            id = id,
            imgCard = null,
            name = name,
            listCard = listCard
        )
    }

}