// Time Complexity : O(n*(w*h)!), n -> Number of buildings to be placed, w -> Width, h -> Height
// Space Complexity : O(w*h)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.LinkedList;
import java.util.Queue;

public class OptimalPlacementOfBuildings {
	int minDistance;

	public int findMinDistance(int height, int width, int buildings) {
		int[][] grid = new int[height][width];
		minDistance = Integer.MAX_VALUE;

		// Fill the grid with -1
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = -1;
			}
		}

		backtrack(grid, 0, 0, height, width, buildings);
		return minDistance;
	}

	private void backtrack(int[][] grid, int row, int col, int height, int width, int buildings) {
		// Base
		if (col == width) {
			row++;
			col = 0;
		}

		if (buildings == 0) {
			bfs(grid, height, width);
			return;
		}
		// Logic
		for (int i = row; i < height; i++) {
			for (int j = col; j < width; j++) {
				// Action
				grid[i][j] = 0;
				// Recurse
				backtrack(grid, i, j + 1, height, width, buildings - 1);
				// Backtrack
				grid[i][j] = -1;
			}
			col = 0;
		}
	}

	private void bfs(int[][] grid, int height, int width) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[height][width];
		int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		int dist = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j] == 0) {
					queue.add(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}

		while (!queue.isEmpty()) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				int[] pair = queue.poll();
				for (int[] dir : dirs) {
					int r = pair[0] + dir[0];
					int c = pair[1] + dir[1];

					if (r >= 0 && r < height && c >= 0 && c < width && !visited[r][c]) {
						queue.add(new int[] { r, c });
						visited[r][c] = true;
					}
				}
			}
			dist++;
		}
		minDistance = Math.min(minDistance, dist - 1);
	}

	public static void main(String[] args) {
		OptimalPlacementOfBuildings obj = new OptimalPlacementOfBuildings();
		int w = 4;
		int h = 4;
		int n = 2;
		System.out.println("Minimum distance: " + obj.findMinDistance(h, w, n));
	}

}
