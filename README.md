# Giphy4J

An unofficial Java library for the [Giphy API](https://github.com/Giphy/GiphyAPI).
This project is a fork of [keshrath's Discord4J](https://github.com/keshrath/Giphy4J) with minor updates since its release.

## Access and API Keys

Take a look at the [Giphy API](https://github.com/Giphy/GiphyAPI) page to get your api key.

* The public beta/test key is "dc6zaTOxFJmzC”

## Example

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchFeed feed = giphy.search("cat", 1, 0);
	
	feed.getDataList().get(0).getImages().getOriginal().getUrl();
```

## Rendition Guide

* fixed_height - Height set to 200px. Good for mobile use.
* fixed_height_still - Static preview image for fixed_height
* fixed_height_downsampled - Height set to 200px. Reduced to 6 frames to minimize file size to the lowest. Works well for unlimited scroll on mobile and as animated previews. See Giphy.com on mobile web as an example.
* fixed_width - Width set to 200px. Good for mobile use.
* fixed_width_still - Static preview image for fixed_width
* fixed_width_downsampled - Width set to 200px. Reduced to 6 frames. Works well for unlimited scroll on mobile and as animated previews.
* fixed_height_small - Height set to 100px. Good for mobile keyboards.
* fixed_height_small_still - Static preview image for fixed_height_small
* fixed_width_small - Width set to 100px. Good for mobile keyboards
* fixed_width_small_still - Static preview image for fixed_width_small
* downsized - File size under 1.5mb.
* downsized_still - Static preview image for downsized
* downsized_medium - File size under 5mb.
* downsized_large - File size under 8mb.
* original - Original file size and file dimensions. Good for desktop use.
* original_still - Preview image for original

## How to Use

Quick showcase how to use the library.

### Search Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchFeed feed = giphy.search("cat", 1, 0);
	
	feed.getDataList().get(0).getImages().getOriginal().getUrl();
```

### Get GIF by ID Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchGiphy giphyData = giphy.searchByID("feqkVgjJpYtjy");
	
	giphyData.getData().getImages().getOriginal().getUrl();
```

### Translate Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchGiphy giphyData = giphy.translate("superman");
	
	giphyData.getData().getImages().getOriginal().getUrl();
```

### Random Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchRandom giphyData = giphy.searchRandom("cat");
	
	giphyData.getData().getImageOriginalUrl();
```

### Trending GIFs Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchFeed feed = giphy.trend();
	
	feed.getDataList().get(0).getImages().getOriginal().getUrl();
```

### STICKER Search Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchFeed feed = giphy.searchSticker("cat", 1, 0);
	
	feed.getDataList().get(0).getImages().getOriginal().getUrl();
```

### STICKER Translate Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchGiphy giphyData = giphy.translateSticker("superman");
	
	giphyData.getData().getImages().getOriginal().getUrl();
```

### STICKER Random Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchRandom giphyData = giphy.trendSticker("cat");
	
	giphyData.getData().getImageOriginalUrl();
```

### STICKER Trending Endpoint

```java
	Giphy giphy = new Giphy(API_KEY);
	
	SearchFeed feed = giphy.trendSticker();
	
	feed.getDataList().get(0).getImages().getOriginal().getUrl();
```

## Download Giphy4J

Giphy4J can either be downloaded as a JAR file or embedded as Maven dependency. 

### Manual Install

Download JAR from [here](https://github.com/keshrath/Giphy4J/tree/master/distribution).

### Maven

```xml
	<dependency>
		<groupId>com.trievosoftware</groupId>
		<artifactId>giphy4j</artifactId>
		<version>1.0.0</version>
	</dependency>
```

### Logging
 Please note, this project utilizes SLF4J, this should allow a user to configure their own loggers, so no fear of this
 library overwriting your current logging configuration, unlike the original version of this library.

