package com.project.catalogingmtgcards.data.repository.getUserDataRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.project.catalogingmtgcards.domain.model.DeckItem

class GetUserDataRepositoryImpl(private val firestore: FirebaseFirestore) {

//    fun getUserData(treinoId: String): LiveData<List<String>> = MutableLiveData<List<String>().apply {
//        firestore.collection("users")
//            .addSnapshotListener { s, _ ->
//                s?.let { snapshot ->
//                    val exercicios: List<Exercicio> = snapshot.documents.mapNotNull { doc ->
//                        doc.toObject<ExercicioDocument>()?.toExercicio(doc.id)
//                    }.filter { it.treinoId == treinoId }
//
//                    value = exercicios
//                }
//            }
//    }

    fun getByDocumentId(id: String): LiveData<List<DeckItem>> = MutableLiveData<List<DeckItem>>().apply {
        firestore.collection("users").addSnapshotListener { s, _ ->
            s?.let { snapshot ->
                var list: MutableList<DeckItem> = mutableListOf()
                snapshot
                    .documents
                    .filter { it.id == id }
                    .mapNotNull { doc ->
                        val deckList = doc.get("deckList") as List<Map<String, Any>>
                        deckList.forEach {
                            val name = it["name"] as String
                            val cardList = it["cardList"] as List<String>
                            list.add(DeckItemDocument(name, cardList).toDeckItem(id))
                        }
                    }
                value = list
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