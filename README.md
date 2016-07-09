# RelativePopupWindow
Android PopupWindow that can be easily layout relative to anchor View.

![Art](art/art1.gif)

## Usage

Extend [RelativePopupWindow](relativepopupwindow/src/main/java/com/labo/kaji/relativepopupwindow/RelativePopupWindow.java) class and call showOnAnchor().

```java
popup.showOnAnchor(anchor, VerticalPosition.TOP, HorizontalPosition.CENTER);
```

## Position Parameters

### VerticalPosition

- TOP
- ALIGN_BOTTOM
- CENTER
- ALIGN_TOP
- BOTTOM

### HorizontalPosition

- LEFT
- ALIGN_RIGHT
- CENTER
- ALIGN_LEFT
- RIGHT

## License

MIT License.