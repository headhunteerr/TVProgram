package com.tv.program.tri;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

import com.tv.program.model.Duree;
import com.tv.program.model.Personne;
import com.tv.program.model.programmes.Programme;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class ProgrammeTrieurTest {
    private final List<Programme> programmes = new ArrayList<>();
    private final int N = 9;

    @Before
    public void init() {
        programmes.clear();
    }

    @Test
    public void parDateDeDebut() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Date date = calendar.getTime();
            Programme programme = mock(Programme.class);
            when(programme.getDateDeDebut())
                    .thenReturn(date);
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParDateDeDebut(programmes);
        assertRightOrder();
    }

    @Test
    public void parDateDeFin() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Date date = calendar.getTime();
            Programme programme = mock(Programme.class);
            when(programme.getDateDeFin())
                    .thenReturn(date);
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParDateDeFin(programmes);
        assertRightOrder();
    }

    @Test
    public void parTitre() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Programme programme = mock(Programme.class);
            when(programme.getTitre())
                    .thenReturn(i + "titre");
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParTitre(programmes);
        assertRightOrder();
    }

    @Test
    public void parAnnee() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Programme programme = mock(Programme.class);
            when(programme.getAnnee())
                    .thenReturn(2000 + i);
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParAnnee(programmes);
        assertRightOrder();
    }

    @Test
    public void parPays() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Programme programme = mock(Programme.class);
            when(programme.getPays())
                    .thenReturn(i + " pays");
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParPays(programmes);
        assertRightOrder();
    }

    @Test
    public void parType() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Programme programme = mock(Programme.class);
            when(programme.getType())
                    .thenReturn(i + " type");
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParType(programmes);
        assertRightOrder();
    }

    @Test
    public void parDuree() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Programme programme = mock(Programme.class);
            when(programme.getDuree())
                    .thenReturn(new Duree("minutes", (i+1)*100));
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParDuree(programmes);
        assertRightOrder();
    }

    @Test
    public void parAspect() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Programme programme = mock(Programme.class);
            when(programme.getAspect())
                    .thenReturn(i + " aspect");
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParAspect(programmes);
        assertRightOrder();
    }

    @Test
    public void parQualite() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < N; i++) {
            Programme programme = mock(Programme.class);
            when(programme.getQualite())
                    .thenReturn(i + " qualite");
            when(programme.getId())
                    .thenReturn((long)i);
            programmes.add(programme);
            calendar.add(Calendar.DATE, 1);
        }

        Collections.shuffle(programmes);
        ProgrammeTrieur.trierParQualitee(programmes);
        assertRightOrder();
    }

    @Test
    public void acteursParApparition() {
        final Personne p1 = new Personne("John Stark", "producer");
        final Personne p2 = new Personne("Einstein", "producer");
        final Personne p3 = new Personne("Moi-meme", "actor");

        final Personne[] personnes = new Personne[] {p1, p2, p3};
        for (Personne personne : personnes) {
            List<Personne> list = new ArrayList<>();
            list.add(personne);
            Programme programme = mock(Programme.class);
            when(programme.getCredits())
                    .thenReturn(list);
            when(programme.getTitre())
                    .thenReturn("un film");
            programmes.add(programme);
        }
        Programme programme = mock(Programme.class);
        when(programme.getCredits())
                .thenReturn(Arrays.asList(personnes));
        when(programme.getTitre())
                .thenReturn("autre-chose");

        programmes.add(programme);

        programmes.get(0).getCredits().add(p3);
        programmes.get(1).getCredits().add(p3);
        programmes.get(2).getCredits().add(p1);

        List<Map.Entry<Personne, Integer>> entries = ProgrammeTrieur
                .acteursParApparition(programmes);

        List<Map.Entry<Personne, Integer>> expected =
                Arrays.asList(newEntry(p3, 4),
                        newEntry(p1, 3),
                        newEntry(p2, 2));

        assertEquals("Should be equal", expected, entries);

    }

    private Map.Entry<Personne, Integer> newEntry(Personne p, Integer i) {
        return new Map.Entry<Personne, Integer>() {
            @Override
            public Personne getKey() {
                return p;
            }

            @Override
            public Integer getValue() {
                return i;
            }

            @Override
            public Integer setValue(Integer value) {
                return null;
            }

            @Override
            public String toString() {
                return getKey() + "=" + getValue();
            }

            @Override
            public boolean equals(Object obj) {
                Map.Entry<Personne, Integer> entry = (Map.Entry<Personne, Integer>) obj;
                return entry.equals(this);
            }
        };
    }

    private void assertRightOrder() {
        for (int i = 0; i < N; i++) {
            assertEquals("Devrait etre egal", i, programmes.get(i).getId());
        }
    }
}
