#include <iostream>
#include <vector>
#include <queue>

void solve (int n, int k, std::vector<std::pair<long long,long long>> const& v) {
    std::priority_queue<long long, std::vector<long long>, std::greater<long long>> queue;
    std::vector<long long> result;

    auto it = v.begin();
    auto last = k >= n ? v.end() : it + k;
    for (; it != last; it++ ) {
        auto ft = it->first + it->second;
        queue.push(ft);
        result.push_back(ft);
    }
    for (;it != v.end();++it) {
        auto min = queue.top();
        auto future = (it->first > min) ? it->first + it->second : min + it->second;
        queue.pop();
        queue.push(future);
        result.push_back(future);
    }

    for(auto &i: result) {
        std::cout << i << std::endl;
    }
}

int main() {
    int n,k;
    std::cin >> n >> k;
    std::vector<std::pair<long long,long long>> v;
    for (int i = 0; i < n; ++i) {
        long long l,r;
        std::cin >> l >> r;
        v.push_back(std::make_pair(l,r));
    }
    solve(n,k,v);
}
