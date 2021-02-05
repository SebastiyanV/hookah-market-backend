package amvisible.hookahmarket.web.controller;

import amvisible.hookahmarket.service.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServerController {
}
