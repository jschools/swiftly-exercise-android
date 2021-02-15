### Swiftly Coding Exercise

#### Run Instructions
1. Clone this repo on a machine with Android Studio and ADB tools
2. Connect an Android device or emulator
3. Build and install the project via one of these options:
    * Run `./gradlew installDebug` from the project root directory, or
    * Load the project in Android Studio and run the `app` configuration with the `debug` variant
     selected
3. Open the app by clicking the "Specials" app. (It has the standard Android Studio placeholder
icon.)

#### Miscellaneous Notes
* Data can be refreshed by a down-swipe gesture or by putting the app in the background and
 resuming.
* Tiles will obey the dimensions given by the service with one exception:
    * If the height of a tile is too small to fit the text, it will be constrained to a hard-coded
     minimum height in order to avoid clipping. This dimension adjusts to accommodate system font
      size.
* The layout is optimized for narrow screens such as phones.
* Only the `debug` build variant is supported at this time.

#### Licensed under Apache 2.0
Copyright 2021 Jonathan Schooler

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.