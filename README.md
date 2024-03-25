# Demonstration

### Add an image using the + multiple selection button.

### You can rename the section and specify a location.

### Photos are saved to local repository and Firebase storage.

https://github.com/Gizcerbes/LocationTest/assets/26376753/777e4abf-f7a9-4155-8b1b-f659e9fb75c4

# Add files to build

### path `buildSrc/src/main/kotlin/Secrets.kt`

#### It has a link to firestore

```
object Environments {
	
	const val FIREBASE_STORAGE = "\" ... \""
	
}
```
### path `app/google-services.json`
#### This is the Firebase configuretion

