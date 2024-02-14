package com.Shrinithi.controller;


import com.Shrinithi.Entity.Appointment;
import com.Shrinithi.Request.AppointmentRequest;
import com.Shrinithi.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    public ResponseEntity<Object> saveAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        if (appointmentRequest == null) {
            throw new RuntimeException("appointment request cannot be null");
        }
        Appointment appointment = appointmentService.saveAppointmentRequest(appointmentRequest);
        return new ResponseEntity<>("Appointment Booked, appointment id : " + appointment.getAppointmentId(), HttpStatus.OK);
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
}

