package com.caldeira.projetofinal.zelda.cotrollers;

@RestController
@RequestMapping("/zelda")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ZeldaController {

    private final ZeldaGetawayService zeldaGetawayService;

    @Autowired
    public ZeldaController(ZeldaController zeldaController){
        this.zeldaGetawayService = zeldaController.zeldaGetawayService;
    }

}