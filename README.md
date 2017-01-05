# Darkroom
Java API for photo editing

## Dependencies
- [ImageMagick] (https://www.imagemagick.org/script/index.php)

## Basic usage  
```java
File input = new File("/path/to/image.jpg");
Darkroom darkroom = new Darkroom(input);
```
Set an output if you want to preserve the original file  
```java
File output = new File("/path/to/output.jpg");
darkroom.setOutput(output);
```
Resize
```java
int width = 800;
int height = 600;
darkroom.resize(width, height);
```
Resize while maintaining the ratio  
```java
int width = 800;
darkroom.resize(width);
```
Limit width to specif size
```java
int maxWidth = 1024;
darkroom.limitSize(maxWidth);
```
Compress, reducing quality
```java
double quality = 20.0;
darkroom.compress(quality);
```
## Applying filters
Filters currently available:
- Gotham
- Kelvin
- Lomo
- Nashvile
- Normalize
- Toaster

By name
```java
String filterName = "Lomo";
darkroom.applyFilter(filterName);
```
By Filter instance
```java
Filter filter = new Kelvin();
darkroom.applyFilter(filter);
```
By enums
```java
Filters filter = Filters.Nashvile;
darkroom.applyFilter(filter);
```
## Exif
Extract Exif metadata from images, if available
```java
Exif exif = darkroom.getExif();
```
Available exif data
- Camera
  - Make
  - Model
- Lens  
  - Make
  - Model
- Location
  - Latitude
  - Longitude
- Focal length
- Focal length in 35mm film
- Exposure time
- Aperture
- ISO value
- Flash info
- White balance
