package com.tv.program.tri;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

import com.tv.program.model.Duree;
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
        assertOrder();
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
        assertOrder();
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
        assertOrder();
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
        assertOrder();
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
        assertOrder();
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
        assertOrder();
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
        assertOrder();
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
        assertOrder();
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
        assertOrder();
    }


    private void assertOrder() {
        for (int i = 0; i < N; i++) {
            assertEquals("Devrait etre egal", i, programmes.get(i).getId());
        }
    }
}
