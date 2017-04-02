# A simple and efficient Java QuadTree implementation

Very usefull and for game development this QuadTree implementation will help you create an efficient collision detection System.

![](http://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Point_quadtree.svg/300px-Point_quadtree.svg.png)

## Installation

### Maven

```xml
<dependency>
  <groupId>org.alwex</groupId>
  <artifactId>quad-tree</artifactId>
  <version>1.3.0</version>
  <type>pom</type>
</dependency>
```

### Gradle

```
compile 'org.alwex:quad-tree:1.3.0'
```

### Ivy
```xml
<dependency org='org.alwex' name='quad-tree' rev='1.3.0'>
  <artifact name='quad-tree' ext='pom' ></artifact>
</dependency>
```

## Usage

initialize the world with a 10 by 10 dimension starting at point (0,0)

```java
// configure the QuadTree
QuadTree.maxItemByNode = 20;
QuadTree.maxLevel = 10;
        
// init the spatial world starting at level 0 (root node)
QuadTree<QuadRectangle> quadTree = new QuadTree<Entity>(new QuadRectangle(0, 0, 10, 10), 0);

// create entities
Entity e1 = new Entity();
Entity e2 = new Entity();
Entity e3 = new Entity();
Entity e4 = new Entity();
Entity e5 = new Entity();
Entity e6 = new Entity();

// push entities to the spatial world
// we defined some rectangle representing the boudaries
// of each entities
quadTree.insert(new QuadRectangle(1, 1, 1, 1), e1);
quadTree.insert(new QuadRectangle(2, 2, 1, 1), e2);
quadTree.insert(new QuadRectangle(4, 4, 1, 1), e3);
quadTree.insert(new QuadRectangle(6, 6, 1, 1), e4);
quadTree.insert(new QuadRectangle(4, 4, 2, 2), e5);

// YES! this QuadTree implementation accept floats
quadTree.insert(new QuadRectangle(0.5f, 6.5f, 0.5f, 0.5f), e6);

// retrieve the matching element within a defined zone
ArrayList<Entity> list = new ArrayList<Entity>();
quadTree.getElements(list, new QuadRectangle(2, 2, 1, 1));
```

## Debug

If you need to debug you QuadTree you can retrieve the different zones created on the fly when element are inserted

```java
ArrayList<QuadRectangle> zoneList = new ArrayList<QuadRectangle>();
quadTree.getAllZones(zoneList);

// display the zones (Libgdx example)
shapeRenderer.setProjectionMatrix(camera.combined);
shapeRenderer.begin(ShapeType.Line);  
for (QuadRectangle z : zoneList) {
    shapeRenderer.rect(
        z.x, 
        z.y, 
        z.width, 
        z.height, 
        Color.GREEN, 
        Color.GREEN, 
        Color.GREEN, 
        Color.GREEN
    );
}
shapeRenderer.end();
```
