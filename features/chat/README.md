
# Firebase Chat module

###### Init firebase
```kotlin
FirebaseApp.initializeApp(this)
FirebaseDatabase.getInstance().setPersistenceEnabled(true)
```

Chat room
-----
###### If you are creating custom recycler item send and item receive

***Received Item***

| Item type | UI component | TAG |
| --------- | ------------ | --- |
| Message | TextView | receive_message |
| Image | ImageView | receive_image |
| Time | TextView | receive_time |
| Attachment | ConstraintView | receive_attachment |
| Attachment Name | TextView | receive_attachment_name |

***Send Item***

| Item type | UI component | TAG |
| --------- | ------------ | --- |
| Message | TextView | send_message |
| Image | ImageView | send_image |
| Time | TextView | send_time |
| Attachment | ConstraintView | send_attachment |
| Attachment Name | TextView | send_attachment_name |

***Eg -***
```xml
    <TextView...
    android:tag="receive_message" />
```
----
###### Initialize chat room

```kotlin
chatManager = ChatManager(this, userId = userID, friendId = friendID)
               .setRecyclerItems(rv_message)
               /*.setRecyclerItems(
                    recycle_view_message_list,
                    R.layout.item_chat_send,
                    R.layout.item_chat_receive
                )*/
            chatManager?.readMessageNormal()
```

###### Start database listener
```kotlin
override fun onStart() {
       super.onStart()
       chatManager?.startListening()
   }
```
 
###### Stop database listener 
```kotlin
override fun onStop() {
        super.onStop()
        chatManager?.stopListening()
    }
```

###### Send text message
```kotlin
override fun onClick(v: View?) {
        val message = et_chat.text.toString()
        et_chat.text.clear()
        if (message.isNotEmpty()) {
            chatManager?.writeMessage(message, userID, TYPE_TEXT)
        }
    }
```

