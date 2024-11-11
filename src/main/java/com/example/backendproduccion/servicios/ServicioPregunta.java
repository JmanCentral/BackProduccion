package com.example.backendproduccion.servicios;

import com.example.backendproduccion.dto.PreguntaDTO;
import com.example.backendproduccion.entidades.Pregunta;
import com.example.backendproduccion.repositorios.RepositorioPregunta;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ServicioPregunta implements Serializable {

    private ModelMapper modelMapper;

    @Autowired
    private RepositorioPregunta repositorioPregunta;

    @PostConstruct
    public void insertAllPreguntas() {
        List<PreguntaDTO> preguntaDTOs = crearPreguntas();

        for (PreguntaDTO dto : preguntaDTOs) {
            // Buscar si la pregunta ya existe en la base de datos
            List<Pregunta> existentes = repositorioPregunta.findByPregunta(dto.getPregunta());

            if (existentes.isEmpty()) {

                Pregunta nuevaPregunta = modelMapper.map(dto, Pregunta.class);
                repositorioPregunta.save(nuevaPregunta);
            } else {

                Pregunta preguntaExistente = existentes.get(0);

                preguntaExistente.setEstado(dto.isEstado());


                repositorioPregunta.save(preguntaExistente);
            }
        }
    }

    private List<PreguntaDTO> crearPreguntas() {
        return List.of(

                // Preguntas fáciles

                new PreguntaDTO(null, "¿Cuánto es 12 ÷ 4?", "1", "2", "3", "4", "3", "matematicas", "facil", true),
                new PreguntaDTO(null, "¿Cuántos lados tiene un triángulo?", "4", "3", "5", "2", "3", "matematicas", "facil" , true),
                new PreguntaDTO(null, "¿Cuál es el número primo más pequeño?", "1", "2", "3", "5", "2", "matematicas", "facil" , true),
                new PreguntaDTO(null, "¿Cuánto es 7 + 7?", "12", "13", "14", "15", "14", "matematicas", "facil" , true),
                new PreguntaDTO(null, "¿Cuánto es 9 - 4?", "4", "5", "6", "7", "5", "matematicas", "facil" , true),
                new PreguntaDTO(null, "¿Cuánto es 8 * 2?", "12", "14", "16", "18", "16", "matematicas", "facil" , false),
                new PreguntaDTO(null, "¿Cuánto es sen (0)?", "0", "1", "-1", "indefinido", "0", "matematicas", "facil" , false),


                // Preguntas intermedias
                new PreguntaDTO(null, "¿Cuánto es 25 * 3?", "60", "70", "75", "80", "75", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Qué es el resultado de 100 ÷ 5?", "10", "15", "20", "25", "20", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuánto es 8^2 (8 al cuadrado)?", "64", "32", "16", "81", "64", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es el valor de la raíz cuadrada de 144?", "10", "11", "12", "13", "12", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuánto es 12 * 12?", "120", "132", "144", "156", "144", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es el valor absoluto de -15?", "15", "-15", "0", "1", "15", "matematicas", "intermedio", true),
                new PreguntaDTO(null, "¿Cuánto es 15 ÷ 0.5?", "30", "7.5", "15", "10", "30", "matematicas", "intermedio", true),
                new PreguntaDTO(null, "¿Cuánto es el perímetro de un cuadrado con lados de 5 cm?", "10 cm", "15 cm", "20 cm", "25 cm", "20 cm", "matematicas", "intermedio", true),
                new PreguntaDTO(null, "¿Cuánto es el área de un círculo con radio 7 (π=3.14)?", "154", "44", "66", "132", "154", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "Si un tren viaja a 80 km/h, ¿cuánto tiempo tardará en recorrer 240 km?", "1 hora", "2 horas", "3 horas", "4 horas", "3 horas", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuánto es 45 ÷ 9?", "3", "4", "5", "6", "5", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuánto es el cubo de 3 (3^3)?", "9", "27", "18", "36", "27", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es el valor de la raíz cúbica de 64?", "3", "4", "5", "6", "4", "matematicas", "intermedio" ,true ),
                new PreguntaDTO(null, "¿Cuánto es 7 * 8?", "54", "56", "64", "72", "56", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuánto es 20% de 200?", "20", "30", "40", "50", "40", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuánto es 9^2 (9 al cuadrado)?", "72", "81", "91", "100", "81", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "Si un automóvil viaja a 90 km/h, ¿cuánto recorrerá en 3 horas?", "180 km", "270 km", "360 km", "450 km", "270 km", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es la suma de los ángulos interiores de un triángulo?", "90°", "120°", "180°", "360°", "180°", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "Si un número es divisible por 2 y 3, ¿por cuál otro número es divisible?", "5", "6", "8", "12", "6", "matematicas", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuánto es 11 * 11?", "110", "111", "121", "131", "121", "matematicas", "intermedio" , true),


                // Preguntas difíciles
                new PreguntaDTO(null, "¿Cuánto es 15 * 17?", "245", "255", "265", "275", "255", "matematicas", "dificil", true),
                new PreguntaDTO(null, "¿Cuánto es la raíz cúbica de 27?", "1", "2", "3", "4", "3", "matematicas", "dificil" , true),
                new PreguntaDTO(null, "¿Qué es el valor de 2^5 (2 elevado a la 5)?", "16", "32", "64", "128", "32", "matematicas", "dificil" , true),
                new PreguntaDTO(null, "Si x = 3 y y = 4, ¿cuál es el valor de 2x + 3y?", "12", "15", "18", "21", "18", "matematicas", "dificil" , true),
                new PreguntaDTO(null, "¿Cuál es la derivada de 3x^2?", "6x", "3x", "9x", "2x", "6x", "matematicas", "dificil" , true),
                new PreguntaDTO(null, "¿Cuál es el seno de 30 grados?", "0.5", "1", "0.707", "0", "0.5", "matematicas", "dificil" ,true ),
                new PreguntaDTO(null, "¿Cuánto es 45 ÷ 1.5?", "20", "25", "30", "35", "30", "matematicas", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es el logaritmo en base 10 de 1000?", "1", "2", "3", "4", "3", "matematicas", "dificil" , true),
                new PreguntaDTO(null, "¿Cuánto es el valor de 7!", "5000", "5040", "4000", "4030", "5040", "matematicas", "dificil" , true),
                new PreguntaDTO(null, "Si a + b = 10 y a - b = 4, ¿cuánto vale a?", "2", "4", "7", "8", "7", "matematicas", "dificil" , true),


                // Preguntas fáciles
                new PreguntaDTO(null, "¿Cuál es el continente más grande?", "África", "América", "Asia", "Europa", "Asia", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿Cuál es el océano más grande del mundo?", "Atlántico", "Índico", "Ártico", "Pacífico", "Pacífico", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿En qué país se encuentra la Torre Eiffel?", "España", "Italia", "Francia", "Reino Unido", "Francia", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿Cuál es la capital de España?", "Madrid", "Barcelona", "Sevilla", "Valencia", "Madrid", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿Cuál es el país más grande de América del Sur?", "Argentina", "Brasil", "Perú", "Chile", "Brasil", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿Cuál es el río más largo del mundo?", "Amazonas", "Nilo", "Misisipi", "Yangtsé", "Nilo", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿En qué continente se encuentra Egipto?", "Europa", "África", "Asia", "Oceanía", "África", "geografia", "facil",true),
                new PreguntaDTO(null, "¿Cuál es el país más poblado del mundo?", "Estados Unidos", "India", "Rusia", "China", "China", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿Cuál es el país más pequeño del mundo?", "Mónaco", "San Marino", "Liechtenstein", "Vaticano", "Vaticano", "geografia", "facil" , true),
                new PreguntaDTO(null, "¿En qué país se encuentra la Estatua de la Libertad?", "Inglaterra", "Francia", "Estados Unidos", "Canadá", "Estados Unidos", "geografia", "facil" , true),

                // Preguntas intermedias
                new PreguntaDTO(null, "¿Cuál es la montaña más alta del mundo?", "Monte Everest", "K2", "Makalu", "Kangchenjunga", "Monte Everest", "geografia", "intermedio" , true),
                new PreguntaDTO(null, "¿En qué país se encuentra el desierto del Sahara?", "Arabia Saudita", "Sudán", "Egipto", "Marruecos", "Egipto", "geografia", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es la capital de Australia?", "Sídney", "Melbourne", "Canberra", "Perth", "Canberra", "geografia", "intermedio" , true),
                new PreguntaDTO(null, "¿En qué continente se encuentra el Monte Kilimanjaro?", "Asia", "Europa", "África", "América del Sur", "África", "geografia", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es el país más grande del mundo?", "Canadá", "China", "Estados Unidos", "Rusia", "Rusia", "geografia", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es la capital de Canadá?", "Toronto", "Vancouver", "Montreal", "Ottawa", "Ottawa", "geografia", "intermedio" ,true ),
                new PreguntaDTO(null, "¿Cuál es el río más largo de América del Sur?", "Paraná", "Amazonas", "Orinoco", "Río de la Plata", "Amazonas", "geografia", "intermedio", true),
                new PreguntaDTO(null, "¿En qué país se encuentra el famoso volcán Monte Fuji?", "China", "Corea del Sur", "Japón", "Filipinas", "Japón", "geografia", "intermedio" , true),
                new PreguntaDTO(null, "¿Cuál es la capital de México?", "Guadalajara", "Monterrey", "Ciudad de México", "Tijuana", "Ciudad de México", "geografia", "intermedio", true),
                new PreguntaDTO(null, "¿En qué país se encuentra la Gran Muralla China?", "Japón", "Corea del Norte", "China", "Mongolia", "China", "geografia", "intermedio" , true),

                // Preguntas difíciles
                new PreguntaDTO(null, "¿Cuál es el país más grande de África?", "Argelia", "Sudáfrica", "Nigeria", "Egipto", "Argelia", "geografia", "dificil" , true),
                new PreguntaDTO(null, "¿Cuál es la capital de Nueva Zelanda?", "Sídney", "Auckland", "Wellington", "Canberra", "Wellington", "geografia", "dificil" , true),
                new PreguntaDTO(null, "¿Qué país tiene más islas en el mundo?", "Filipinas", "Suecia", "Noruega", "Indonesia", "Suecia", "geografia", "dificil" , true),
                new PreguntaDTO(null, "¿Cuál es el lago más profundo del mundo?", "Lago Baikal", "Lago Superior", "Lago Tanganica", "Lago Victoria", "Lago Baikal", "geografia", "dificil" , true),
                new PreguntaDTO(null, "¿En qué país se encuentra el Monte Aconcagua?", "Chile", "Perú", "Argentina", "Bolivia", "Argentina", "geografia", "dificil" , true),
                new PreguntaDTO(null, "¿Cuál es el país más pequeño de América del Sur?", "Uruguay", "Surinam", "Guyana", "Paraguay", "Surinam", "geografia", "dificil" , true),
                new PreguntaDTO(null, "¿Qué cordillera separa a Europa de Asia?", "Alpes", "Cáucaso", "Urales", "Himalayas", "Urales", "geografia", "dificil" ,true ),
                new PreguntaDTO(null, "¿Cuál es el desierto más grande de Asia?", "Desierto de Gobi", "Desierto de Thar", "Desierto del Karakum", "Desierto Arábigo", "Desierto de Gobi", "geografia", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es la capital de Mongolia?", "Ulán Bator", "Astana", "Taskent", "Biskek", "Ulán Bator", "geografia", "dificil" , true),
                new PreguntaDTO(null, "¿Cuál es el país más poblado de África?", "Egipto", "Sudáfrica", "Nigeria", "Etiopía", "Nigeria", "geografia", "dificil" , true),


                // Preguntas fáciles
                new PreguntaDTO(null, "¿Quién escribió 'Don Quijote de la Mancha'?", "Gabriel García Márquez", "Miguel de Cervantes", "Federico García Lorca", "Lope de Vega", "Miguel de Cervantes", "literatura", "facil" , true),
                new PreguntaDTO(null, "¿Quién es el autor de 'Cien años de soledad'?", "Jorge Luis Borges", "Mario Vargas Llosa", "Gabriel García Márquez", "Pablo Neruda", "Gabriel García Márquez", "literatura", "facil", true),
                new PreguntaDTO(null, "¿Cuál es el nombre del detective creado por Arthur Conan Doyle?", "Hercule Poirot", "Sherlock Holmes", "Philip Marlowe", "Sam Spade", "Sherlock Holmes", "literatura", "facil", true),
                new PreguntaDTO(null, "¿Quién escribió 'Romeo y Julieta'?", "William Shakespeare", "Christopher Marlowe", "Charles Dickens", "Jane Austen", "William Shakespeare", "literatura", "facil" , true),
                new PreguntaDTO(null, "¿Quién escribió 'Matar a un ruiseñor'?", "George Orwell", "Harper Lee", "Ernest Hemingway", "F. Scott Fitzgerald", "Harper Lee", "literatura", "facil", true),
                new PreguntaDTO(null, "¿Cuál es el nombre del personaje principal de 'Moby Dick'?", "Capitán Ahab", "Ishmael", "Queequeg", "Starbuck", "Capitán Ahab", "literatura", "facil", true),
                new PreguntaDTO(null, "¿Quién escribió 'La Odisea'?", "Sófocles", "Homero", "Virgilio", "Eurípides", "Homero", "literatura", "facil",true),
                new PreguntaDTO(null, "¿En qué país nació el poeta Pablo Neruda?", "México", "Colombia", "Chile", "Argentina", "Chile", "literatura", "facil",true),
                new PreguntaDTO(null, "¿Quién escribió 'Orgullo y prejuicio'?", "Jane Austen", "Emily Brontë", "Mary Shelley", "Virginia Woolf", "Jane Austen", "literatura", "facil",true),
                new PreguntaDTO(null, "¿Quién es el autor de 'La metamorfosis'?", "Franz Kafka", "Friedrich Nietzsche", "Thomas Mann", "Jean-Paul Sartre", "Franz Kafka", "literatura", "facil",true),

                // Preguntas intermedias
                new PreguntaDTO(null, "¿Quién es el autor de 'El gran Gatsby'?", "John Steinbeck", "F. Scott Fitzgerald", "Ernest Hemingway", "William Faulkner", "F. Scott Fitzgerald", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Quién escribió 'Crimen y castigo'?", "Lev Tolstói", "Antón Chéjov", "Fiódor Dostoyevski", "Iván Turguénev", "Fiódor Dostoyevski", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Quién escribió 'El retrato de Dorian Gray'?", "Oscar Wilde", "George Bernard Shaw", "James Joyce", "Samuel Beckett", "Oscar Wilde", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Quién es el autor de la trilogía 'El Señor de los Anillos'?", "J.R.R. Tolkien", "C.S. Lewis", "George R.R. Martin", "Philip Pullman", "J.R.R. Tolkien", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Qué obra literaria narra la historia de Jean Valjean?", "Los Miserables", "El Conde de Montecristo", "La guerra y la paz", "Madame Bovary", "Los Miserables", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Quién es el autor de '1984'?", "Aldous Huxley", "Ray Bradbury", "George Orwell", "H.G. Wells", "George Orwell", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Quién escribió 'El amor en los tiempos del cólera'?", "Isabel Allende", "Gabriel García Márquez", "Mario Vargas Llosa", "Julio Cortázar", "Gabriel García Márquez", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Quién escribió 'Cumbres Borrascosas'?", "Emily Brontë", "Charlotte Brontë", "Jane Austen", "Louisa May Alcott", "Emily Brontë", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿Cuál es el título original de 'Orgullo y prejuicio'?", "Pride and Honor", "Pride and Prejudice", "Prejudiced Pride", "Sense and Prejudice", "Pride and Prejudice", "literatura", "intermedio",true),
                new PreguntaDTO(null, "¿En qué libro aparece el personaje 'Gregorio Samsa'?", "El proceso", "El castillo", "La metamorfosis", "América", "La metamorfosis", "literatura", "intermedio",true),

                // Preguntas difíciles
                new PreguntaDTO(null, "¿Quién escribió 'Ulises'?", "James Joyce", "Samuel Beckett", "Virginia Woolf", "T.S. Eliot", "James Joyce", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es el verdadero nombre de Mark Twain?", "Samuel Langhorne Clemens", "Edgar Allan Poe", "William Faulkner", "Henry James", "Samuel Langhorne Clemens", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Quién escribió 'La montaña mágica'?", "Franz Kafka", "Thomas Mann", "Hermann Hesse", "Stefan Zweig", "Thomas Mann", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Qué poeta escribió 'Canto a mí mismo'?", "Walt Whitman", "Emily Dickinson", "Robert Frost", "Ezra Pound", "Walt Whitman", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Qué novela de Marcel Proust forma parte de 'En busca del tiempo perdido'?", "El tiempo recobrado", "A la sombra de las muchachas en flor", "Por el camino de Swann", "La prisionera", "Por el camino de Swann", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Quién escribió 'Las uvas de la ira'?", "Ernest Hemingway", "John Steinbeck", "William Faulkner", "F. Scott Fitzgerald", "John Steinbeck", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es el título de la famosa obra de teatro escrita por Tennessee Williams?", "Un tranvía llamado deseo", "Muerte de un viajante", "El zoo de cristal", "La gata sobre el tejado de zinc", "Un tranvía llamado deseo", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Qué obra escribió Friedrich Nietzsche?", "Así habló Zaratustra", "El extranjero", "El ser y la nada", "Crimen y castigo", "Así habló Zaratustra", "literatura", "dificil",true),
                new PreguntaDTO(null, "¿Quién escribió 'Madame Bovary'?", "Émile Zola", "Honoré de Balzac", "Gustave Flaubert", "Guy de Maupassant", "Gustave Flaubert", "literatura", "dificil",true ),
                new PreguntaDTO(null, "¿Quién escribió 'El ruido y la furia'?", "Ernest Hemingway", "William Faulkner", "John Dos Passos", "F. Scott Fitzgerald", "William Faulkner", "literatura", "dificil",true),

                // Preguntas fáciles
                new PreguntaDTO(null, "¿Quién es el protagonista de la serie 'Breaking Bad'?", "Jesse Pinkman", "Saul Goodman", "Hank Schrader", "Walter White", "Walter White", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Cuál es la famosa saga de películas donde aparece 'Darth Vader'?", "Harry Potter", "El Señor de los Anillos", "Star Wars", "Matrix", "Star Wars", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Cómo se llama el personaje principal de 'Toy Story'?", "Buzz Lightyear", "Woody", "Rex", "Slinky", "Woody", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Quién es el creador del personaje 'Sherlock Holmes'?", "Agatha Christie", "Arthur Conan Doyle", "Edgar Allan Poe", "Raymond Chandler", "Arthur Conan Doyle", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿En qué película animada aparece el personaje de Simba?", "Shrek", "El Rey León", "Madagascar", "Buscando a Nemo", "El Rey León", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Cuál es el nombre del actor que interpreta a 'Jack Sparrow' en 'Piratas del Caribe'?", "Orlando Bloom", "Johnny Depp", "Brad Pitt", "Tom Cruise", "Johnny Depp", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Quién es el villano principal de 'Los Vengadores: Infinity War'?", "Loki", "Thanos", "Ultrón", "Hela", "Thanos", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Qué banda interpretó la canción 'Bohemian Rhapsody'?", "The Beatles", "Led Zeppelin", "Queen", "Pink Floyd", "Queen", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Qué película ganó el Óscar a la mejor película en 2020?", "1917", "Joker", "Parasite", "Once Upon a Time in Hollywood", "Parasite", "entretenimiento", "facil",true),
                new PreguntaDTO(null, "¿Cuál es el verdadero nombre de 'Iron Man'?", "Steve Rogers", "Tony Stark", "Bruce Wayne", "Peter Parker", "Tony Stark", "entretenimiento", "facil",true),

                // Preguntas intermedias
                new PreguntaDTO(null, "¿Quién dirigió la película 'Inception'?", "Martin Scorsese", "Steven Spielberg", "Christopher Nolan", "Quentin Tarantino", "Christopher Nolan", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿En qué año se estrenó la película 'Titanic'?", "1997", "1999", "2001", "1995", "1997", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿Qué actor interpretó al Joker en 'El caballero de la noche'?", "Jack Nicholson", "Joaquin Phoenix", "Heath Ledger", "Jared Leto", "Heath Ledger", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿Cuál es el nombre real de la cantante conocida como 'Lady Gaga'?", "Stefani Joanne Angelina Germanotta", "Robyn Fenty", "Alecia Beth Moore", "Katheryn Hudson", "Stefani Joanne Angelina Germanotta", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿Quién es el creador de la serie animada 'Los Simpson'?", "Matt Groening", "Seth MacFarlane", "Trey Parker", "Mike Judge", "Matt Groening", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿Cuál es el nombre del actor que interpretó a 'Forrest Gump'?", "Tom Hanks", "Brad Pitt", "Leonardo DiCaprio", "George Clooney", "Tom Hanks", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿En qué película de Pixar aparece el personaje de 'Wall-E'?", "Ratatouille", "Up", "Wall-E", "Monsters, Inc.", "Wall-E", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿Qué famoso director de cine es conocido por películas como 'Pulp Fiction' y 'Kill Bill'?", "Christopher Nolan", "Quentin Tarantino", "Steven Spielberg", "James Cameron", "Quentin Tarantino", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿Quién ganó el Grammy al mejor álbum del año en 2021?", "Taylor Swift", "Beyoncé", "Billie Eilish", "Dua Lipa", "Taylor Swift", "entretenimiento", "intermedio",true),
                new PreguntaDTO(null, "¿Cuál es el nombre de la ciudad ficticia donde vive Batman?", "Metropolis", "Gotham", "Star City", "Central City", "Gotham", "entretenimiento", "intermedio",true),

                // Preguntas difíciles
                new PreguntaDTO(null, "¿Qué banda sonora compuso Ennio Morricone para la película 'El bueno, el feo y el malo'?", "La misión", "Cinema Paradiso", "El bueno, el feo y el malo", "Por un puñado de dólares", "El bueno, el feo y el malo", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es la primera película de la saga de James Bond?", "Goldfinger", "Dr. No", "Thunderball", "Desde Rusia con amor", "Dr. No", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es el nombre del personaje interpretado por Clint Eastwood en 'El bueno, el feo y el malo'?", "El hombre sin nombre", "William Munny", "Harry Callahan", "Joe Kidd", "El hombre sin nombre", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Qué película de Akira Kurosawa inspiró el western 'Los siete magníficos'?", "Trono de sangre", "Yojimbo", "Ran", "Los siete samuráis", "Los siete samuráis", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Quién escribió el guion de la película 'Taxi Driver'?", "Quentin Tarantino", "Paul Schrader", "Francis Ford Coppola", "Stanley Kubrick", "Paul Schrader", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Quién es el autor de la novela 'El padrino', que inspiró la famosa película?", "Truman Capote", "Ernest Hemingway", "Mario Puzo", "John Steinbeck", "Mario Puzo", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Quién ganó el primer premio Óscar como mejor actriz en 1929?", "Greta Garbo", "Claudette Colbert", "Mary Pickford", "Janet Gaynor", "Janet Gaynor", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es el verdadero nombre de Freddie Mercury, el vocalista de Queen?", "Farrokh Bulsara", "Zoroaster Mercury", "Fred Balsar", "Farid Mercuria", "Farrokh Bulsara", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es el nombre de la película ganadora del Óscar a mejor película en 2015?", "Birdman", "Spotlight", "La La Land", "Mad Max: Fury Road", "Birdman", "entretenimiento", "dificil",true),
                new PreguntaDTO(null, "¿Cuál fue la primera película animada en ganar un Óscar?", "Blanca Nieves y los siete enanitos", "Pinocho", "Fantasía", "Bambi", "Blanca Nieves y los siete enanitos", "entretenimiento", "dificil",true),


                // Preguntas fáciles
                new PreguntaDTO(null, "¿Cuántos jugadores tiene un equipo de fútbol en el campo?", "9", "10", "11", "12", "11", "deportes", "facil",true),
                new PreguntaDTO(null, "¿En qué país se celebraron los Juegos Olímpicos de 2016?", "Brasil", "China", "Grecia", "Japón", "Brasil", "deportes", "facil",true),
                new PreguntaDTO(null, "¿Qué deporte practica Roger Federer?", "Tenis", "Fútbol", "Baloncesto", "Ciclismo", "Tenis", "deportes", "facil",true),
                new PreguntaDTO(null, "¿Cómo se llama la liga profesional de baloncesto en Estados Unidos?", "NFL", "MLB", "NBA", "NHL", "NBA", "deportes", "facil",true),
                new PreguntaDTO(null, "¿Qué país ha ganado más Copas del Mundo de fútbol?", "Italia", "Argentina", "Brasil", "Alemania", "Brasil", "deportes", "facil",true),
                new PreguntaDTO(null, "¿Cuántos puntos vale un gol en el fútbol?", "1", "2", "3", "4", "1", "deportes", "facil",true),
                new PreguntaDTO(null, "¿Qué selección ganó la Copa del Mundo en 2018?", "Brasil", "Francia", "Argentina", "Alemania", "Francia", "deportes", "facil",true),
                new PreguntaDTO(null, "¿Quién es conocido como 'El Rey del Fútbol'?", "Lionel Messi", "Diego Maradona", "Pelé", "Cristiano Ronaldo", "Pelé", "deportes", "facil",true),
                new PreguntaDTO(null, "¿En qué deporte se utiliza una raqueta y una pelota pequeña?", "Tenis de mesa", "Bádminton", "Tenis", "Squash", "Tenis", "deportes", "facil",true),
                new PreguntaDTO(null, "¿Qué jugador es apodado 'La Pulga'?", "Cristiano Ronaldo", "Lionel Messi", "Neymar", "Zinedine Zidane", "Lionel Messi", "deportes", "facil",true),

                // Preguntas intermedias
                new PreguntaDTO(null, "¿Cuántos anillos de la NBA ha ganado Michael Jordan?", "5", "6", "7", "4", "6", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿En qué año ganó España su primer Mundial de fútbol?", "2006", "2010", "2014", "2002", "2010", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿Quién tiene el récord de más goles en la historia de los Mundiales?", "Pelé", "Ronaldo", "Miroslav Klose", "Lionel Messi", "Miroslav Klose", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿Qué equipo de fútbol ganó la UEFA Champions League en 2020?", "Liverpool", "Bayern Múnich", "PSG", "Real Madrid", "Bayern Múnich", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿En qué deporte compitió el legendario atleta Michael Phelps?", "Atletismo", "Ciclismo", "Natación", "Gimnasia", "Natación", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿En qué año se celebraron los primeros Juegos Olímpicos modernos?", "1896", "1900", "1912", "1888", "1896", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿Quién es el jugador con más títulos de Grand Slam en la historia del tenis masculino?", "Rafael Nadal", "Roger Federer", "Novak Djokovic", "Pete Sampras", "Novak Djokovic", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿Qué selección de fútbol ha ganado más veces la Eurocopa?", "España", "Alemania", "Francia", "Italia", "Alemania", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿Quién ganó el Mundial de Fórmula 1 en 2021?", "Max Verstappen", "Lewis Hamilton", "Sebastian Vettel", "Valtteri Bottas", "Max Verstappen", "deportes", "intermedio",true),
                new PreguntaDTO(null, "¿Qué equipo de la NFL ganó el Super Bowl en 2020?", "Kansas City Chiefs", "San Francisco 49ers", "New England Patriots", "Los Angeles Rams", "Kansas City Chiefs", "deportes", "intermedio",true),

                // Preguntas difíciles
                new PreguntaDTO(null, "¿Quién es el único jugador en la historia en ganar tres Copas del Mundo de fútbol?", "Maradona", "Pelé", "Zidane", "Beckenbauer", "Pelé", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿Cuál es el récord de medallas olímpicas de Michael Phelps?", "23", "25", "28", "30", "28", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿Qué club ha ganado más títulos de la UEFA Champions League?", "Barcelona", "Real Madrid", "Manchester United", "AC Milan", "Real Madrid", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿En qué año ganó Roger Federer su primer título de Grand Slam?", "2002", "2003", "2004", "2005", "2003", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿Quién fue el primer campeón mundial de Fórmula 1?", "Niki Lauda", "Juan Manuel Fangio", "Giuseppe Farina", "Alain Prost", "Giuseppe Farina", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿En qué país se inventó el baloncesto?", "Estados Unidos", "Canadá", "Francia", "Inglaterra", "Canadá", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿Qué selección de rugby ha ganado más veces la Copa del Mundo?", "Australia", "Inglaterra", "Nueva Zelanda", "Sudáfrica", "Nueva Zelanda", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿Quién es el máximo goleador histórico de la NBA?", "Kobe Bryant", "Michael Jordan", "Karl Malone", "Kareem Abdul-Jabbar", "Kareem Abdul-Jabbar", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿Quién es el tenista más joven en ganar un título de Grand Slam?", "Boris Becker", "Rafael Nadal", "Pete Sampras", "Björn Borg", "Boris Becker", "deportes", "dificil",true),
                new PreguntaDTO(null, "¿Qué piloto ha ganado más títulos de MotoGP?", "Valentino Rossi", "Marc Márquez", "Giacomo Agostini", "Mick Doohan", "Giacomo Agostini", "deportes", "dificil",true)

        );
    }

    public List<PreguntaDTO> getTodasLasPreguntas(String categoria , String dificultad) {
        List<Pregunta> preguntas = repositorioPregunta.findByCategoriaAndDificultadAndEstado(categoria , dificultad , true);

        Collections.shuffle(preguntas);

        List<Pregunta> preguntasAleatorias = preguntas.subList(0, Math.min(10, preguntas.size()));

        return preguntasAleatorias.stream()
                .map(pregunta -> modelMapper.map(pregunta, PreguntaDTO.class))
                .collect(Collectors.toList());
    }

}

