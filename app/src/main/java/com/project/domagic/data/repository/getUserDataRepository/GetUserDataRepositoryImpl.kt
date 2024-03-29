package com.project.domagic.data.repository.getUserDataRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.project.domagic.data.repository.FirestoreConstants.CARD_LIST_FROM_DECK
import com.project.domagic.data.repository.FirestoreConstants.COLLECTION_USERS
import com.project.domagic.data.repository.FirestoreConstants.DECK_LIST_NAME
import com.project.domagic.data.repository.FirestoreConstants.DOC_DECK_LIST
import com.project.domagic.domain.model.DeckItem

class GetUserDataRepositoryImpl(private val firestore: FirebaseFirestore) : GetUserDataRepository {

    override fun getDeckByDocumentId(): LiveData<List<DeckItem>> =
        MutableLiveData<List<DeckItem>>().apply {
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
                                    val deckId = it["deckId"] as String
                                    list.add(
                                        DeckItemDocument(name, cardList, deckId).toDeckItem(
                                            user.uid
                                        )
                                    )
                                }
                            }
                        value = list
                    }
                }

            }
        }

    override fun getCardListDeckDetail(deckId: String): LiveData<List<String>> =
        MutableLiveData<List<String>>().apply {
            firestore.collection(COLLECTION_USERS).addSnapshotListener { s, _ ->
                Firebase.auth.currentUser?.let { user ->
                    s?.let { snapshot ->
                        var list: List<String> = mutableListOf()
                        snapshot
                            .documents
                            .filter { it.id == user.uid }
                            .mapNotNull { doc ->
                                val deckList = doc.get(DOC_DECK_LIST) as List<Map<String, Any>>
                                list = deckList.first { it["deckId"] as String == deckId }
                                    .filterKeys { it.contains(CARD_LIST_FROM_DECK) }[CARD_LIST_FROM_DECK] as List<String>
                            }
                        value = list
                    }
                }

            }
        }

    private class DeckItemDocument(
        val name: String = "",
        val listCard: List<String>,
        val deckId: String
    ) {
        fun toDeckItem(id: String) = DeckItem(
            userId = id,
            imgCard = null,
            name = name,
            listCard = listCard,
            deckId = deckId
        )
    }

}