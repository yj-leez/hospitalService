package hospital.hospitalserivce.service;

import hospital.hospitalserivce.domain.*;
import hospital.hospitalserivce.repository.BookingRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookingServiceTest {
    @PersistenceContext EntityManager em;

    @Autowired BookingService bookingService;
    @Autowired BookingRepository bookingRepository;

    @Test
    public void 예약() throws Exception{
        //given
        Member member = getNewMember();
        Hospital hospital = getNewHospital();
        Department department = getNewDepartment(hospital);
        Doctor doctor = getNewDoctor(department);

        //when
        Long bookingId = bookingService.booking(member.getId(), doctor.getId());

        //then
        Booking findBooking = bookingRepository.findOne(bookingId);
        assertEquals("예약시 상태는 READY", BookingStatus.READY, findBooking.getStatus());
        assertEquals("예약한 진료과의 이름이 internal", "internal", findBooking.getDoctor().getDepartment().getName());
        //이렇게 가져와도 되나?


    }

    @Test
    public void 진료완료() throws Exception{
        //given
        Member member = getNewMember();
        Hospital hospital = getNewHospital();
        Department department = getNewDepartment(hospital);
        Doctor doctor = getNewDoctor(department);

        //when
        Long bookingId = bookingService.booking(member.getId(), doctor.getId());
        bookingService.completedTreatment(bookingId);

        //then
        Booking findBooking = bookingRepository.findOne(bookingId);
        assertEquals("진료 완료시 상태는 COMP이다", BookingStatus.COMP, findBooking.getStatus());
    }
    private Doctor getNewDoctor(Department department) {
        Doctor doctor = new Doctor();
        doctor.setName("Kim");
        doctor.setDepartment(department);
        doctor.setCareer(7L);
        em.persist(doctor);
        return doctor;
    }

    private Department getNewDepartment(Hospital hospital) {
        Department department = new Department();
        department.setName("internal");
        department.setHospital(hospital);
        department.setNumber(120);
        em.persist(department);
        return department;
    }

    private Hospital getNewHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("hospitalA");
        hospital.setAddress("Seoul");
        em.persist(hospital);
        return hospital;
    }

    private Member getNewMember() {
        Member member = new Member();
        member.setName("Jo");
        member.setAge(22);
        member.setGender("F");
        em.persist(member);
        return member;
    }
}