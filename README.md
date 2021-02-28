
# News (with paging)
Sample news application to see news list and articles 

On clicking the app icon after installation you will see the list of top headlines. 
Clicking on each news item will lead you to the complete article

two other apis are called for likes and comments per article you can find those in the article page 
at the top right corner on action bar

## Build
To build this application locally you will need to provide an API key to use the [NewsAPI](https://newsapi.org/).
 To do so you can put the generated API key to Constants file under the name API_KEY.

import the project into Android Studio (3.1+)

## Error handling
Errors are either displayed in a tect view (for listing page) or in a toast message format.

## Automated Testing
Since the application doesn't contains any complex api calls with inputs required. The testing is kept simple
and only reserved to ui and its flow. 
Roboelectric is used for the same and simple iteration of standard activity flow checks are implemented.


## Libraries
* [Android Support Library](https://developer.android.com/topic/libraries/support-library/features)
* [Paging Library](https://developer.android.com/topic/libraries/architecture/paging/)
* [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/)
* [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
* [Cardview](https://developer.android.com/guide/topics/ui/layout/cardview)
* [Retrofit2](https://square.github.io/retrofit/) for network requests

Certain other architecture components libs are used for lifecycle aware features