package ua.cursor.filmrate.rateservice.dto;

public interface Views {


    interface Rate {
        interface Id {

        }

        interface Create {
        }

        interface Default {

        }

        interface Full extends Id, Default {
        }
    }

    interface Review {
        interface Id {

        }

        interface Create {
        }

        interface Default {

        }

        interface Full extends Id, Default {
        }
    }
}
