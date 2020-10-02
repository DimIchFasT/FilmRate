package ua.cursor.filmrate.movieservice.dto;

public interface Views {
    interface All {
        interface Id {
        }

        interface Default {
        }

        interface Full extends Id, Default {

        }
    }

    interface Category {
        interface Full extends Views.All.Full {
        }

        interface Default extends Views.All.Default {
        }

    }

    interface Movie {

        interface Full extends Views.All.Full {
        }

        interface Default extends Views.All.Default {
        }

    }
}