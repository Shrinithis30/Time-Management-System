package com.Shrinithi.service;

import com.Shrinithi.Entity.Appointment;
import com.Shrinithi.Repository.AppointmentRepository;
import com.Shrinithi.Request.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointmentRequest(AppointmentRequest request){
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(generateAppointmentId(request.getName()));
        appointment.setCustomerName(request.getName());
        appointment.setCustomerPhone(request.getPhone());
        appointment.setEmail(request.getEmail());
        appointment.setCity(request.getCity());
        appointment.setConsulted(false);
        return appointmentRepository.save(appointment);
    }

    private String generateAppointmentId(String name) {
        Random random = new Random();
        int randomNumber = random.nextInt(0,9);
        return "APP" + name + randomNumber;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public ResponseEntity<Object> updateAppointment(String appointmentId) {

        /* Get appointment data for given appointmentId */
        Appointment appointmentForUpdating = appointmentRepository.findByAppointmentId(appointmentId);
        if(appointmentForUpdating == null ){
            return new ResponseEntity<>("Appointment not found!", HttpStatus.NOT_FOUND);
        }
        /* change value to true  */
        appointmentForUpdating.setConsulted(true);
        /* save the row to table */
        appointmentRepository.save(appointmentForUpdating);
        return new ResponseEntity<>("Appointment updated successfully!", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteAppointment(String appointmentId) {

        /* Get appointment data for given appointmentId */
        Appointment appointmentForDeleting = appointmentRepository.findByAppointmentId(appointmentId);
        if(appointmentForDeleting == null ){
            return new ResponseEntity<>("Appointment not found!", HttpStatus.NOT_FOUND);
        }

        appointmentRepository.deleteByAppointmentId(appointmentId);

        return new ResponseEntity<>("Appointment deleted successfully!", HttpStatus.OK);
    }
}
