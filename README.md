VISUAL PATHFINDING

This project aims for a visual representation of the **result(!)** of the A-Star algorithm with JavaFX. <br>**(The process of the algorithm itself (heuristics, visited fields etc...) will not be visualized)**<br>
The Goal is that the "player" can select a start and a target point on a map full of obstacles.
After that a robot will walk the shortest path between those two points.<br><br>

This project uses a slightly modified version of <b>2DPlayground</b> which includes the A-Star algorithm that uses a slightly modified version of <b>GenericBinaryHeap</b>.<br>
Both projects can be found in this repository.


**Current State:**<br>
JavaFX:<br>
- Playground can be rendered<br>
- Mouse following field marker implemented

<br>Backend:<br>
- A-Star implemented and tested<br>
- levelgenerator for different sizes (8x8, 12x8 and 16x8). Levelgenerator uses A*-Star. Implemented and tested visually. 




