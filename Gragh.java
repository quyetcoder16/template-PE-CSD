import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

void dijkstra(int src, int dest, RandomAccessFile f) throws Exception {
    int[] dist = new int[n];
    boolean[] visited = new boolean[n];
    int[] parent = new int[n];
    int INF = 99;
    Arrays.fill(dist, INF);
    Arrays.fill(visited, false);
    Arrays.fill(parent, -1);
    dist[src] = 0;
    parent[src] = -1;

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
    pq.add(new int[] { src, 0 });
    while (!pq.isEmpty()) {
        int u = pq.poll()[0];

        if (visited[u]) {
            continue;
        }

        visited[u] = true;

        if (dist[u] == INF) {
            continue;
        }
        for (int v = 0; v < n; v++) {
            if (!visited[v]
                    && a[u][v] != 0
                    && dist[v] > dist[u] + a[u][v]) {
                dist[v] = dist[u] + a[u][v];
                parent[v] = u;
                pq.add(new int[] { v, dist[v] });
            }
        }
    }

    int count = 0;
    int[] path = new int[n];
    int tmp = dest;
    while (tmp != -1) {
        path[count] = tmp;
        tmp = parent[tmp];
        count++;
    }

    for (int i = count - 1; i >= 0; i--) {
        fvisit(path[i], f);
    }
    f.writeBytes("\n");
    for (int i = count - 1; i >= 0; i--) {
        f.writeBytes(" " + dist[path[i]]);
    }
}

// find set
void dijkstra2(int src, int dest, RandomAccessFile f) throws Exception {
    int[] dist = new int[n];
    boolean[] visited = new boolean[n];
    int[] parent = new int[n];
    int INF = 99;
    Arrays.fill(dist, INF);
    Arrays.fill(visited, false);
    Arrays.fill(parent, -1);
    dist[src] = 0;
    parent[src] = -1;

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
    int[] resv = new int[2 * n];
    int[] resdi = new int[2 * n];
    int length = 0;
    pq.add(new int[] { src, 0 });
    while (!pq.isEmpty()) {
        int distu = pq.peek()[1];
        int u = pq.poll()[0];
        if (visited[u]) {
            continue;
        }

        visited[u] = true;

        if (dist[u] == INF) {
            continue;
        }

        resv[length] = u;
        resdi[length] = distu;
        length++;

        for (int v = 0; v < n; v++) {
            if (!visited[v]
                    && a[u][v] != 0
                    && dist[v] > dist[u] + a[u][v]) {
                dist[v] = dist[u] + a[u][v];
                parent[v] = u;
                pq.add(new int[] { v, dist[v] });
            }
        }
    }

    for (int i = length - 3; i < length; i++) {
        fvisit(resv[i], resdi[i], f);
    }

}

// -- tim eulerCycle

void findEulerianCycle(int start) {
    if (!hasEulerianCycle()) {
        System.out.println("Đồ thị không có chu trình Euler.");
        return;
    }
    Stack<Integer> stack = new Stack<>();
    ArrayList<Integer> cycle = new ArrayList<>();
    stack.push(start);
    while (!stack.isEmpty()) {
        int u = stack.peek();
        int i;
        for (i = 0; i < n; i++) {
            if (a[u][i] != 0) {
                stack.push(i);
                a[u][i] = a[i][u] = 0;
                break;
            }
        }
        if (i == n) {
            cycle.add(stack.pop());
        }
    }
    System.out.print("Chu trình Euler: ");
    for (int v : cycle) {
        System.out.print(v + " ");
    }
}

// euler cycle
boolean hasIsolated() {
    for (int i = 0; i < n; i++) {
        if (deg(i) == 0) {
            return (true);
        }
    }
    return (false);
}

boolean isConnected() {
    boolean[] visited = new boolean[n];
    // Stack<Integer> stack = new Stack<>();
    Stack stack = new Stack();
    for (int i = 0; i < n; i++) {
        visited[i] = false;
    }
    stack.push(0);
    visited[0] = true;

    while (!stack.isEmpty()) {
        int current = stack.pop();
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[current][i] > 0) {
                stack.push(i);
                visited[i] = true;
            }
        }
    }

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            return false;
        }
    }
    return true;
}

// kiem tra do thi có vo huong khong
boolean isUnDirected() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] != a[j][i]) {
                return false;
            }
        }
    }
    return true;
}

// kiem tra tat ca dinh co chan khong
boolean allDegEven() {
    for (int i = 0; i < n; i++) {
        if (deg(i) % 2 == 1) {
            return (false);
        }
    }
    return (true);
}

// kiem tra co euler cycle khong
boolean hasEulerCycle() {
    if (!hasIsolated() && isUnDirected() && isConnected() && allDegEven()) {
        return (true);
    } else {
        return (false);
    }
}

void eulerCycle(int fro, RandomAccessFile f) throws IOException {
    if (!hasEulerCycle()) {
        return;
    }

    int[] eulerPath = new int[100];
    int index = 0;

    Stack stack = new Stack();
    stack.push(fro);

    while (!stack.isEmpty()) {
        int current = stack.top();
        int i;

        for (i = 0; i < n; i++) {
            if (a[current][i] > 0) {
                break;
            }
        }

        if (i == n) {
            stack.pop();
            eulerPath[index++] = current;
        } else {
            stack.push(i);
            a[current][i]--;
            a[i][current]--;
        }
    }
    for (int i = 0; i < index; i++) {
        f.writeBytes(v[eulerPath[i]] + " ");
    }
}