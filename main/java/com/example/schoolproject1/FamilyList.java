package com.example.schoolproject1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FamilyList {
    List<Family> familyList;

    public FamilyList(){
        List<Family>familyList= new List<Family>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Family> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Family family) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Family> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Family> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Family get(int i) {
                return null;
            }

            @Override
            public Family set(int i, Family family) {
                return null;
            }

            @Override
            public void add(int i, Family family) {

            }

            @Override
            public Family remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Family> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Family> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Family> subList(int i, int i1) {
                return null;
            }
        };

    }
    public  List<Family> getFamilyList(){
        return familyList;
    }

    public  void setFamilyList(List<Family>familyList){
         this.familyList= familyList;
    }

}
