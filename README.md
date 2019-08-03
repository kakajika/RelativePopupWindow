# RelativePopupWindow

[![wercker status](https://app.wercker.com/status/dd38c76d499fee2384c2cdcba37223ba/s/master)](https://app.wercker.com/project/byKey/dd38c76d499fee2384c2cdcba37223ba)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
<img src="https://img.shields.io/badge/license-MIT-green.svg?style=flat">
[![API](https://img.shields.io/badge/API-9%2B-yellow.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RelativePopupWindow-green.svg?style=true)](https://android-arsenal.com/details/1/3908)
[ ![Download](https://api.bintray.com/packages/kakajika/maven/relativepopupwindow/images/download.svg) ](https://bintray.com/kakajika/maven/relativepopupwindow/_latestVersion)

Android PopupWindow that can be easily located relative to anchor View.

![Art](art/art1.gif)

## How To Use

Extend [RelativePopupWindow](relativepopupwindow/src/main/java/com/labo/kaji/relativepopupwindow/RelativePopupWindow.java) class and call showOnAnchor().

```java
popup.showOnAnchor(anchor, VerticalPosition.ABOVE, HorizontalPosition.CENTER);
```

See detail example in [ExampleCardPopup.kt](example/src/main/kotlin/com/labo/kaji/relativepopupwindow/example/ExampleCardPopup.kt)
or in Java, [ExampleCardPopup.java](example/src/main/java/com/labo/kaji/relativepopupwindow/example/java/ExampleCardPopup.java).

## Position Parameters

### VerticalPosition

- ABOVE
- ALIGN_BOTTOM
- CENTER
- ALIGN_TOP
- BELOW

### HorizontalPosition

- LEFT
- ALIGN_RIGHT
- CENTER
- ALIGN_LEFT
- RIGHT

### Fit In Screen

If you want Popup not to fit in screen automatically, add parameter to disable it.

```java
popup.showOnAnchor(anchor, VerticalPosition.ABOVE, HorizontalPosition.CENTER, false);
```

## Install

This library is available in jcenter.

- for AndroidX:

```groovy
implementation 'com.labo.kaji:relativepopupwindow:0.4.1'
```

- for legacy Support Library:

```groovy
implementation 'com.labo.kaji:relativepopupwindow:0.3.1'
```

## License

MIT License.
