package com.Shrinithi.Repository;


import com.Shrinithi.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByAppointmentId(String appointmentId);
    @Transactional
    void deleteByAppointmentId(String appointmentId);


}

