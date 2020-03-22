package com.proposta.aceita.crmservice.entities.enums;

public enum State {
    AC {
        String getName() {
           return "Acre";
        }
    },
    AL {
        String getName() {
            return "Alagoas";
        }
    },
    AP {
        String getName() {
            return "Amapá";
        }
    },
    AM {
        String getName() {
            return "Amazonas";
        }
    },
    BA {
        String getName() {
            return "Bahia";
        }
    },
    CE {
        String getName() {
            return "Ceará";
        }
    },
    DF {
        String getName() {
            return "Distrito Federal";
        }
    },
    ES {
        String getName() {
            return "Espírito Santo";
        }
    },
    GO {
        String getName() {
            return "Goiás";
        }
    },
    MA {
        String getName() {
            return "Maranhão";
        }
    },
    MT {
        String getName() {
            return "Mato Grosso";
        }
    },
    MS {
        String getName() {
            return "Mato Grosso do Sul";
        }
    },
    MG {
        String getName() {
            return "Minas Gerais";
        }
    },
    PA {
        String getName() {
            return "Pará";
        }
    },
    PB {
        String getName() {
            return "Paraíba";
        }
    },
    PR {
        String getName() {
            return "Paraná";
        }
    },
    PE {
        String getName() {
            return "Pernambuco";
        }
    },
    PI {
        String getName() {
            return "Piauí";
        }
    },
    RJ {
        String getName() {
            return "Rio de Janeiro";
        }
    },
    RN {
        String getName() {
            return "Rio Grande do Norte";
        }
    },
    RS {
        String getName() {
            return "Rio Grande do Sul";
        }
    },
    RO {
        String getName() {
            return "Rondônia";
        }
    },
    RR {
        String getName() {
            return "Roraima";
        }
    },
    SC {
        String getName() {
            return "Santa Catarina";
        }
    },
    SP {
        String getName() {
            return "São Paulo";
        }
    },
    SE {
        String getName() {
            return "Sergipe";
        }
    },
    TO {
        String getName() {
            return "Tocantins";
        }
    };

    abstract String getName();

}
