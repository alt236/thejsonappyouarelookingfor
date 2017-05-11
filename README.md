

### Overview
It is a simple app which loads and displays a simple feed.
It is composed of multiple modules, with each module being responsible for one thing
* {app} - Android - The actual app
* {loader} - Android - this module abstracts the loading process between net and storage
* {storage} - Android - simple local cache. I used a KV store to keep it simple.
* {net} - Java] - the responsible for accessing the feed online
* {model} - Java - common model files. In this instance the JSON pojos

The app UI uses internal UI objects to abstract the datastore. The conversion happens in AlbumAdapter.class.

### Dataloading
The data loading process is as follows:
1. Try to go online
1. If successful, cache the results and pass them on to the UI
1. If failed, see if there is a cached copy and pass that
1. If not notify the error.

This logic can be seen in DataProvider.class.

Data loading gives UI indication and will present error messages (not very pretty) if something goes wrong.
In addition, the list display has different states depending on whether:
1. There was an error on data load
1. There were results (happy path)
1. There were no results, but the call was OK (empty) 

### Abstractions
With the exception of the Dagger modules, the {app} does now know about Picasso (which is hidden behind a generic ImageFetcher interface), retrofit or Hawk (both are hidden behind a Loader facade).

### Other
* Reusable components, such as GSON, ImageLoader, and DataLoader are injectable via Dagger2
* There are build flavours, with classes being overridden as needed.
* There are only a few tests due to time shortage, but the repo is CId via circle.ci

### Libraries:
* Butterknife, 
* Dagger2
* HawkKV
* Picasso, 
* Retrofit 2, 
* AutoFitTextView, 
* GSON, 
* Mockito


If I had more time, I would have:
* made sure that the network library is completely abstracted from the {loader} module (currently I am using retrofit's callback), 
* made the Retrofit instance injectable. Atm is is a singleton in the network module
* added some espresso tests
