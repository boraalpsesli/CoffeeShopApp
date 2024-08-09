package com.example.coffeeshopapp.viewmodel

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.model.CategoryModel
import com.example.coffeeshopapp.model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _popular = MutableLiveData<MutableList<ItemsModel>>()
    private val _offer = MutableLiveData<MutableList<ItemsModel>>()
    private val _favourites = MutableLiveData<MutableList<ItemsModel>>()

    private val _itemFavStatus = MutableLiveData<Boolean>()
    val itemFavStatus: LiveData<Boolean> get() = _itemFavStatus

    val category: LiveData<MutableList<CategoryModel>> = _category
    val popular: LiveData<MutableList<ItemsModel>> = _popular
    val offer: LiveData<MutableList<ItemsModel>> = _offer
    val favourites: LiveData<MutableList<ItemsModel>> = _favourites


    fun loadCategory() {
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadPopular() {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadOffer() {
        val Ref = firebaseDatabase.getReference("Offers")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _offer.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun addFav(title: String, isFav: Boolean) {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.orderByChild("title").equalTo(title)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        childSnapshot.ref.child("isFav").setValue(isFav)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

    }

    fun loadFavourites() {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.orderByChild("isFav").equalTo(true).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _favourites.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }

    fun fetchFavoriteStatus(title: String) {
        val ref = firebaseDatabase.getReference("Items").orderByChild("title").equalTo(title)
            .limitToFirst(1)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val itemSnapshot = snapshot.children.firstOrNull()
                    _itemFavStatus.value =
                        itemSnapshot?.child("isFav")?.getValue(Boolean::class.java) ?: false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }


    fun getFavDrawable(context: Context, isFav: Boolean): Drawable? {
        val drawableResId = if (isFav) {
            R.drawable.favorite_red5
        } else {
            R.drawable.favorite_white
        }
        return AppCompatResources.getDrawable(context, drawableResId)
    }
}
