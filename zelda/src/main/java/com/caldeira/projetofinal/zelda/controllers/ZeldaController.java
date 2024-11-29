package com.caldeira.projetofinal.zelda.cotrollers;

@RestController
@RequestMapping("/zelda")
public class ZeldaController {

    @Autowired
    private final ZeldaGetawayService zeldaGetawayService;


    public ZeldaController(ZeldaController zeldaController){
        this.zeldaGetawayService = zeldaController.zeldaGetawayService;
    }

}