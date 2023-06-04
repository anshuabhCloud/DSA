package Atlassian;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class SnakeGame {
    //    int[] headPosition = new int[]{0,0};
//    int[] tailPosition = new int[]{0,0};
    Deque<int[]> snake = new LinkedList<>();
    int[][] board;
    Map<String, int[]> dirMap = new HashMap<>();
    int width, height;
    int[][] foods;
    int foodPosition = 1;


    public SnakeGame(int width, int height, int[][] food) {
        board = new int[height][width];
        this.width = width;
        this.height = height;
        this.foods = food;
        int[][] dir = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        String[] direction = new String[]{"D", "R", "U", "L"};
        dirMap = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            dirMap.put(direction[i], dir[i]);
        }
        snake.addFirst(new int[]{0, 0});
        if (food.length > 0)
            board[food[0][0]][foods[0][1]] = 1;
    }

    public int move(String direction) {
        int[][] borad = this.board;
        int[] dir = dirMap.get(direction);
        int[] headPosition = snake.peekFirst();
        int[] nextPosition = new int[]{headPosition[0] + dir[0], headPosition[1] + dir[1]};
        if ((nextPosition[0] < 0 || nextPosition[0] >= height)
                || (nextPosition[1] < 0 || nextPosition[1] >= width))
            return -1;
        boolean ifFood = board[nextPosition[0]][nextPosition[1]] == 1;
        if (!ifFood) {
            int[] tailPosition = snake.removeLast();
            board[tailPosition[0]][tailPosition[1]] = 0;
        } else if (foodPosition < foods.length) {
//            if (board[foods[foodPosition][0]][foods[foodPosition][1]] == -1)
//                return -1;
            board[foods[foodPosition][0]][foods[foodPosition][1]] = 1;
            foodPosition++;
//            if (foodPosition == foods.length) {
//                foodPosition = 0;
//            }

        }
        if (board[nextPosition[0]][nextPosition[1]] == -1)
            return -1;

        board[nextPosition[0]][nextPosition[1]] = -1;
        snake.addFirst(nextPosition);
        return snake.size() - 1;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});

        String[] move = new String[]{"R", "D", "R", "U", "L", "U"};
        for (String dir : move)
            System.out.println(snakeGame.move(dir));
    }
}