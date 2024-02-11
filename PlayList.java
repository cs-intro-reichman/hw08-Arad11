import javax.xml.parsers.FactoryConfigurationError;

/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        for (int i = 0; i < tracks.length; i++) {
            if (tracks[i] != null) {
                tracks[i] = track;
                size++;
                return true;
            }
        }

        return false;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        String newStr = "";
        for (int i = 0; i < tracks.length; i++) {
            if (tracks[i] != null) {
                newStr += tracks[i].toString() + "\r";
            }
        }

        return newStr;
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        for(int i = tracks.length - 1; i >= 0; i--) {
            if(tracks[i] != null) {
                tracks[i] = null;
                size--;
                return;
            }
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int total = 0;
        for(int i = 0; i < tracks.length; i++) {
            if(tracks[i] != null) {
                total += tracks[i].getDuration();
            }
        }

        return total;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        for(int i = 0; i< tracks.length; i++) {
            if(tracks[i] != null) {
                if (tracks[i].getTitle().equals(title)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) { // arad
        if(i < 0 || i > tracks.length | size == maxSize) {
            return false;
        } else if(size == 0) {
            if(i == 0) {
                tracks[0] = track;
                return true;
            }
        }
        
        for(int j = tracks.length - 2; j >= i; j++) {
                //arad
        }

        return false;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) { // arad
        if(i < 0 || i > maxSize || size ==0) {
            return;
        } // arad
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) { // arad
        //// replace this comment with your code
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if(size == 0) {
            return;
        }

        for(int i = 0; i < maxSize; i++) {
            if(tracks[i] != null) {
                tracks[i] = null;
                return;
            }
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        if(other.size > this.maxSize) {
            return;
        }

        for(int i = 0; i < other.size; i ++) {
            this.tracks[i] = other.getTrack(i);
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) { 
        if(start < 0 || start > maxSize) {
            return -1;
        }

        int min = tracks[start].getDuration();
        int current;
        for(int i = start + 1; i < maxSize; i++) {
            current = tracks[i].getDuration();
            if(min > current) {
                min = current;
            }
        }

        return min;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        Track temp;
        int min;
        for(int i = 0; i < maxSize; i++) {
            min = minIndex(i);
            temp = this.tracks[i];
            tracks[i] = this.tracks[min];
            this.tracks[min] = temp;
        }
    }
}
