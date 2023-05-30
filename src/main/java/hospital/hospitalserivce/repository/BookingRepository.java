package hospital.hospitalserivce.repository;

import hospital.hospitalserivce.domain.Booking;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookingRepository {
    private final EntityManager em;

    public void save(Booking booking){
        em.persist(booking);
    }

    public Booking findOne(Long id){
        return em.find(Booking.class, id);
    }
}
