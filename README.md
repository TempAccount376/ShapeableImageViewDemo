# ShapeableImageView Demo

This is a project to demonstrate how ShapeableImageView works.

ShapeableImageView supports two corner families `CornerFamily.ROUNDED` and `CornerFamily.CUT`. 
The code to change the shape is minimal and does not need different shape file, just add an overlay in `styles.xml` and then apply it using the `app:shapeAppearanceOverlay` attribute
Use can also use kotlin to apply the desired shape and also change it dynamically.

![](demo.gif)

***Important Note*** `app:shapeAppearanceOverlay` is working as a ThemeOverlay. ShapeAppearanceOverlay applies the changes without affecting the active theme.
THe key to achieve this behaviour is to leave parent attribute empty

## Getting started
Add the dependency for the material library

```groovy
implementation 'com.google.android.material:material:1.2.1'
```

## Usage

There are two ways oif defining the shape. Either define overlays in xml or apply the changes dynamically in kotlin.

### In xml
```xml
<style name="ShapeAppearanceOverlay.App.Circle" parent="">
    <item name="cornerSize">50%</item>
</style>

<style name="ShapeAppearanceOverlay.App.Rounded" parent="">
    <item name="cornerFamily">rounded</item>
    <item name="cornerSize">8dp</item>
</style>

<style name="ShapeAppearanceOverlay.App.BottomLeftTopRightCut" parent="">
    <item name="cornerFamilyTopRight">cut</item>
    <item name="cornerSizeTopRight">80dp</item>
    <item name="cornerFamilyBottomLeft">cut</item>
    <item name="cornerSizeBottomLeft">80dp</item>
</style>
```
and then 
```xml
<com.google.android.material.imageview.ShapeableImageView
            android:id="@+id/imageView"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:scaleType="centerCrop"
            app:srcCompat="@drawable/sample_image"
            app:strokeWidth="4dp"
            app:strokeColor="@color/colorAccent"
            app:shapeAppearanceOverlay="@style/ShapeAppearanceOverlay.App.Circle" />
```

### In kotlin
```kotlin
val cornerSize = resources.getDimension(R.dimen.corner_size)
val shapeAppearanceModel = imageView.shapeAppearanceModel.toBuilder()
    .setAllCorners(CornerFamily.ROUNDED, cornerSize)
    .build()

imageView.shapeAppearanceModel = shapeAppearanceModel

val cornerSize = resources.getDimension(R.dimen.corner_size)
val shapeAppearanceModel = imageView.shapeAppearanceModel.toBuilder()
    .setTopRightCorner(CornerFamily.ROUNDED, cornerSize)
    .setTopLeftCorner(CornerFamily.CUT, cornerSize)
    .setBottomRightCorner(CornerFamily.ROUNDED, cornerSize)
    .setBottomLeftCorner(CornerFamily.CUT, cornerSize)
    .build()

imageView.shapeAppearanceModel = shapeAppearanceModel
```



