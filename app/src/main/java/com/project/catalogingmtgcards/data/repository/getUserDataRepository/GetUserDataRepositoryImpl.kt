package com.project.catalogingmtgcards.data.repository.getUserDataRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.project.catalogingmtgcards.data.repository.FirestoreConstants.CARD_LIST_FROM_DECK
import com.project.catalogingmtgcards.data.repository.FirestoreConstants.COLLECTION_USERS
import com.project.catalogingmtgcards.data.repository.FirestoreConstants.DECK_LIST_NAME
import com.project.catalogingmtgcards.data.repository.FirestoreConstants.DOC_DECK_LIST
import com.project.catalogingmtgcards.domain.model.DeckItem

class GetUserDataRepositoryImpl(private val firestore: FirebaseFirestore): GetUserDataRepository {

    override fun getDeckByDocumentId(): LiveData<List<DeckItem>> = MutableLiveData<List<DeckItem>>().apply {
        firestore.collection(COLLECTION_USERS).addSnapshotListener { s, _ ->
            Firebase.auth.currentUser?.let { user ->
                s?.let { snapshot ->
                    var list: MutableList<DeckItem> = mutableListOf()
                    snapshot
                        .documents
                        .filter { it.id == user.uid }
                        .mapNotNull { doc ->
                            val deckList = doc.get(DOC_DECK_LIST) as List<Map<String, Any>>
                            deckList.forEach {
                                val name = it[DECK_LIST_NAME] as String
                                val cardList = it[CARD_LIST_FROM_DECK] as List<String>
                                list.add(DeckItemDocument(name, cardList).toDeckItem(user.uid))
                            }
                        }
                    value = list
                }
            }

        }
    }

    override fun getCardListDeckDetail(index: Int): LiveData<List<String>> = MutableLiveData<List<String>>().apply {
        firestore.collection(COLLECTION_USERS).addSnapshotListener { s, _ ->
            Firebase.auth.currentUser?.let { user ->
                s?.let { snapshot ->
                    var list: List<String> = mutableListOf()
                    snapshot
                        .documents
                        .filter { it.id == user.uid }
                        .mapNotNull { doc ->
                            val deckList = doc.get(DOC_DECK_LIST) as List<Map<String, Any>>
                            list = deckList[index][CARD_LIST_FROM_DECK] as List<String>
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