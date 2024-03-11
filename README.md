# sample-planet-listing-app

![Copy of Copy of Movie App](https://github.com/roym5252/sample-planet-listing-app/assets/30793139/1b42dbc9-f4a3-4b0c-9ff1-0899e21244d1)


## Architecture

Modern Android App Architecture is used because it clearly separates the app into three layers: UI, Domain, and Data. This makes it easy to write unit tests for the Domain layer, and to keep all UI elements in the UI layer.

A core module is created to keep all common code, and separate modules are created for each screen/feature. This structure allows new modules to be added based on features in the future, and keeps module-specific UI elements and tests within the related module. All common code is kept under the core module.

Benefits:

- Future scalability
- Code maintainability
- Separation of layers
- Module re-usability

Link: https://developer.android.com/topic/architecture

## Technologies:

- Jetpack Compose

- Coroutines

- Flow

- Hilt

- ViewModel

- Coroutines

- Room

- Retrofit

- Gson

- OkHttp

- HttpInterceptor

- EncryptedSharedPreferences

- Robolectric

- AndroidX Test

- Mockito

- MockWebServer

- Timber

- Leak Canary

- EncryptedSharedPreference

- Macrobenchmark


## TDD (Test Driven Development)

TDD approach was consistently employed throughout the app development process. Initially, failing unit tests were crafted, followed by the implementation of basic code to make the tests pass. Subsequently, the code underwent refactoring to adhere to the specified requirements. The Red-Green-Refactor pattern was adhered to as much as possible.


## Levels of testing

- Unit tests are written with Robolectric, which enables efficient UI testing without the need for instrumentation tests.

- Unit tests cover both screen components and data layer use cases. Mockito is used for mocking objects, and MockWebServer is employed for simulating server responses.

- A macrobenchmark test is incorporated to ensure the app's startup performance remains optimized.


## Accessibility

- Minimum font size of 12 sp is used across the app.

- Click labels are added.

- 'mergeDescendants = true' is used wherever applicable.

- 'contentDescription' is added in appropriate areas.

- Clickable areas are kept large.

## Security

- A dummy API key is added and is secured inside local.properties file and it is added to gitignore. For CI, the file is uploaded to Gitlab's secured file location and CI process access this file from the secured location. This protects the API key from getting exposed.

- During app launch, if build is release version, app will check if the device is an emulator or any rooted device. If yes, app will stop execution.

- API key is loaded into EncryptedSharedPreference from local.properties. Although it can be directly accessed without loading to EncryptedSharedPreference, this is done to demonstrate knowledge in securing sensitive information within app using EncryptedSharedPreference.

- Proguard is enabled for release variant.


## Performance

- Macrobenchmarking is done to ensure the performance of app start up. This will help to compare the app performance in future with previous start up time to determine if the changes done affects the app's performance.

StartUp Test results (Samsung S23):

timeToInitialDisplayMs   min 250.3,   median 275.6,   max 291.5

Additional details: https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview

- Resource shrinking is enabled for release variant. This will ensure smaller APK, which will eventually result in better app performance.

- Leak canary library is added to capture all sorts of memory leaks. This is to ensure that the memory leaks are identified on time and will not affect the performance of the app.

- Hilt is used for dependency injection of objects, which will help to avoid unnecessary creation of objects.

## Continuous Integration

- CI is set up using Github Actions.

- File containing sensitive information (API key) is added to Github's secure variable section and connected with Github Actions.

## Notes

- Mock server data is used.

- Dark mode theme implementation not included.

## API key configuration

- Place below line in the local.properties file and replace the API key. The content of the local.properties file is added Github Action's secret section for CI/CD to work properly.

API_KEY=PLACE_API_KEY_HERE

## Future enhancements

- Data security: Enhance data security by employing SQLCipher on top of Room.
  
- Performance: Include Baseline profile for faster loading of the app after installation.
