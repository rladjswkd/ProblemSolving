const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((v) => +v));
const [n, m] = input.shift();
const edges = input.sort((a, b) => a[0] - b[0]);
let target;
const TARGET = 2;

class MinHeap {
  constructor() {
    this.arr = [null];
    this.size = 0;
  }

  push(arr) {
    this.arr.push(arr);
    this.size++;

    let curr = this.size;
    let parent = Math.floor(curr / 2);

    while (curr !== 1) {
      if (this.arr[curr][TARGET] < this.arr[parent][TARGET]) {
        [this.arr[curr], this.arr[parent]] = [this.arr[parent], this.arr[curr]];
      }
      curr = parent;
      parent = Math.floor(parent / 2);
    }
  }

  pop() {
    if (this.size === 0) {
      return;
    } else if (this.size === 1) {
      this.arr.pop();
      this.size--;
      return;
    }
    this.arr[1] = this.arr[this.size];
    this.arr.pop();
    let curr = 1;
    let left = curr * 2;
    let right = left + 1;

    while (true) {
      if (this.arr[left] === undefined) {
        break;
      } else if (this.arr[right] === undefined) {
        if (this.arr[curr][TARGET] > this.arr[left][TARGET]) {
          [this.arr[curr], this.arr[left]] = [this.arr[left], this.arr[curr]];
        }
        curr = left;
        left = curr * 2;
        right = left + 1;
      } else {
        const isLeftSmall = this.arr[left][TARGET] < this.arr[right][TARGET];
        if (isLeftSmall) {
          if (this.arr[curr][TARGET] > this.arr[left][TARGET]) {
            [this.arr[curr], this.arr[left]] = [this.arr[left], this.arr[curr]];
          }
          curr = left;
        } else {
          if (this.arr[curr][TARGET] > this.arr[right][TARGET]) {
            [this.arr[curr], this.arr[right]] = [
              this.arr[right],
              this.arr[curr],
            ];
          }
          curr = right;
        }
        left = curr * 2;
        right = left + 1;
      }
    }
    this.size--;
  }

  top() {
    return this.arr[this.size === 0 ? 0 : 1];
  }
}

// 다익스트라 + 5000개를 브루트 포스? 시간 초과가 날까

const adjacent = new Array(n + 1).fill(null).map(() => new Array(0));

for (let i = 0; i < edges.length; i++) {
  const [start, end, cost] = edges[i];
  adjacent[start].push([end, cost, i]);
  adjacent[end].push([start, cost, i]);
}

function solve(flag = false, exclude = 0) {
  const shortest = new Array(n + 1).fill(Infinity);
  const prev = new Array(n + 1).fill(0);
  shortest[1] = 0;
  const minHeap = new MinHeap();

  // 시작이 1
  for (const [dst, cost, i] of adjacent[1]) {
    if (flag && i === exclude) {
    } else {
      minHeap.push([1, dst, cost]);
    }
  }

  // 다익스트라
  while (minHeap.size) {
    // 우선순위 큐에서 누적
    const [start, end, cost] = minHeap.top();
    minHeap.pop();
    // 최단 거리를 업데이트할 수 있으면
    if (shortest[end] > cost) {
      // 업데이트
      shortest[end] = cost;
      // 현재 업데이트한 끝 정점의 이전 배열에 시작 정점을 저장
      prev[end] = start;
      // 인접 배열 순회
      for (const [nextEnd, nextCost, i] of adjacent[end]) {
        const prevCost = shortest[end];
        // 제외된 간선 및 최단 거리 확정 노드 제외
        if (
          nextCost === Infinity ||
          (flag && i === exclude) ||
          shortest[nextEnd] !== Infinity
        ) {
          continue;
        }
        minHeap.push([end, nextEnd, prevCost + nextCost]);
      }
    }
  }

  if (!flag) {
    const arr = [n];
    let start = n;
    while (start !== 1) {
      arr.push(prev[start]);
      start = prev[start];
    }
    target = arr.reverse();
  }
  return shortest[n];
}

const least = solve();
let max = 0;
const candidates = [];

for (let i = 0; i < target.length - 1; i++) {
  const start = target[i];
  const end = target[i + 1];

  for (const [dst, cost, i] of adjacent[start]) {
    if (dst === end) {
      candidates.push([start, dst, i]);
    }
  }
}

for (const [start, end, i] of candidates) {
  const time = solve(true, i);
  max = Math.max(max, time);
  if (max === Infinity) {
    break;
  }
}

console.log(max === Infinity ? -1 : max - least);