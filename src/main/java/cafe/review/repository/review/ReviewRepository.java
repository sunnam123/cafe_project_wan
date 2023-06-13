package cafe.review.repository.review;

import cafe.review.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewRepository implements ReviewInterface{

    private static final Map<Long, Review> store = new HashMap<>();

    private static long sequence=0l;

    @Override
    public Review save(Review review) {
        review.setReviewId(++sequence);
        store.put(review.getReviewId(), review);
        return review;
    }

    @Override
    public List<Review> findAll() {
        return new ArrayList<>(store.values());
    }
}
