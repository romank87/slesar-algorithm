#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

struct range {
    int begin;
    int end;
    double prob;
    int level;
};

struct Point {
    int pos;
    double prob;
    enum eType { BEGIN, END } type;
};

bool operator >(Point const& lhs, Point const& rhs) {
    if (lhs.pos == rhs.pos) {
        return lhs.type < rhs.type;
    }

    return lhs.pos > rhs.pos;
}

double getRangeProb (int value, std::vector<range> const& v) {
    auto begin = v.begin();
    auto end = v.end();

    while (end - begin > 1) {
        auto middle = begin + (end - begin)/2;
        if (value >= middle->begin) {
            begin = middle;
        } else {
            end = middle;
        }
    }

    if (value >= begin->begin && value < begin->end) {
        return begin->prob;
    }
    return 1.0;
}

int main() {
    int n,m;
    std::cin >> n >> m;

    std::vector<Point> points;
    points.reserve(n*4);
    int pos, height, leftProb, rightProb;
    Point point;
    for (int i =0; i< n; i++) {
        std::cin >> pos >> height >> leftProb >> rightProb;

        if (leftProb != 0) {
            point.type = Point::eType::BEGIN;
            point.pos = pos - height;
            point.prob = (100 - leftProb) * 0.01;
            points.push_back(point);

            point.type = Point::eType::END;
            point.pos = pos;
            point.prob = (100 - leftProb) * 0.01;
            points.push_back(point);
        }
        if (rightProb != 0) {
            point.type = Point::eType::BEGIN;
            point.pos = pos + 1;
            point.prob = (100 - rightProb) * 0.01;
            points.push_back(point);

            point.type = Point::eType::END;
            point.pos = pos + height + 1;
            point.prob = (100 - rightProb) * 0.01;
            points.push_back(point);
        }
    }
    std::priority_queue<Point, std::vector<Point>, std::greater<Point>> queue(points.begin(), points.end());
//    while (!queue.empty()) {
//        Point const& p = queue.top();
//        const char *type = p.type == Point::eType::BEGIN ? "BEGIN" : "END";
//        std::cout << queue.top().pos  << ", " << type << ", prob: " << p.prob <<  std::endl;
//        queue.pop();
//    }


    std::vector<range> ranges;
    ranges.reserve(n*16);

    range curr_range;
    curr_range.begin = INT32_MIN;
    curr_range.end = INT32_MIN;
    curr_range.level = 0;
    curr_range.prob = 1.0;
    while(!queue.empty()) {
        Point const& p = queue.top();
        if (p.type == Point::eType::BEGIN) {
            if (p.pos == curr_range.begin && curr_range.level > 0) {
                curr_range.prob *= p.prob;
                curr_range.level += 1;
            } else if (curr_range.level > 0) {
                curr_range.end = p.pos;
                ranges.push_back(curr_range);

                curr_range.begin = p.pos;
                curr_range.prob *= p.prob;
                curr_range.level +=1;
            } else {
                curr_range.begin = p.pos;
                curr_range.prob = p.prob;
                curr_range.level = 1;
            }
        } else {
            if (curr_range.end != p.pos) {
                curr_range.end = p.pos;
                ranges.push_back(curr_range);
            }

            curr_range.level-=1;
            if (curr_range.level > 0) {
                curr_range.begin = p.pos;
                curr_range.prob /= p.prob;
            }
        }
        queue.pop();
    }
//
//    for (auto const& r: ranges ) {
//        std::cout << "[" << r.begin << "," << r.end << "), prob = " << r.prob << ", level = " << r.level << "\n";
//    }

    double res = 0.0;
    int mpos, power;
    for (int i =0; i< m; ++i) {
        std::cin >> mpos >> power;
        double prob = getRangeProb(mpos, ranges);
        double value = prob*power;
        res += value;

//        std::cout << "Prob for " << mpos << " = " << prob << ", value = " << prob*power << " \n";
    }

    if (std::isnan (res)) {
        std::cout << 0.0 << std::endl;
    } else {
        std::cout << res << std::endl;
    }
}