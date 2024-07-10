만약, 선분이 두 개가 아니라 여러 개일 땐 각 선분들의 교점을 찾는 문제를 어떻게 풀 수 있을까?
두 번째 시도 방식으로 모든 선분들을 둘 씩 짝지어 확인하면 시간 복잡도는 선분이 $n$개일 때, $O(n^2)$가 될 것이다.

이런 방식은 각 선분이 나머지 모든 선분들과 교차한다면 적절한 방식으로 볼 수 있지만, 실제로 교차하는 선분이 몇 개 없을 땐 시간 비효율적이다.

여기서 알아볼 것은 _output-sensitive algorithm_, 즉, 출력의 수(여기선 교점의 수)에 비례하는 연산 수를 갖는 알고리즘이다.

1. 선분 $s_1 ... s_n$이 있을 때, 각 선분의 y축에 대한 정사영을 고려해보자.  
   두 선분의 y축에 대한 정사영이 서로 겹치는 부분이 없다면, 두 선분은 교차할 수 없다.  
   따라서 y축에 대한 정사영이 겹치는 두 선분에 대해서만 교점을 찾는 연산을 수행하면 된다.
2. 이를 위해 x축에 평행한 가상의 직선 `l`을 어떤 선분보다도 위에서부터 쓸어내리며(sweep) `l`과 교차하는 선분들을 찾는다.

이러한 알고리즘을 `plane sweep algorithm`이라 하며 가상의 직선 l을 `sweep line`이라고 한다.

sweep line의 status는 sweep line과 교차하는 선분들의 집합을 나타낸다.

1. 각 선분에 대해 한 끝 점이 `l`과 교차하면, 이 선분을 status에 추가한다.
2. `l`을 쓸어내리며 status에 포함된 선분들에 대해 교점을 찾는다.
3. status에 포함된 선분의 반대쪽 끝 점과 `l`이 교차할 때, 이 선분에 대해 교점을 찾는 것을 그만두고 status에서 제거한다.

하지만, 이 것만으로는 충분하지 않다.
모든 선분이 다 y축에 평행하다면 이 방식은 여전히 "교점에 비례한 연산 횟수"를 갖지 못한다.

미완성