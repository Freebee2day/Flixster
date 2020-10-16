
package com.example.flixster.models;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated("org.parceler.ParcelAnnotationProcessor")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Movies$$Parcelable
    implements Parcelable, ParcelWrapper<com.example.flixster.models.Movies>
{

    private com.example.flixster.models.Movies movies$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Movies$$Parcelable>CREATOR = new Creator<Movies$$Parcelable>() {


        @Override
        public Movies$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Movies$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Movies$$Parcelable[] newArray(int size) {
            return new Movies$$Parcelable[size] ;
        }

    }
    ;

    public Movies$$Parcelable(com.example.flixster.models.Movies movies$$2) {
        movies$$0 = movies$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(movies$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.example.flixster.models.Movies movies$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(movies$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(movies$$1));
            parcel$$1 .writeString(movies$$1 .overview);
            parcel$$1 .writeInt(movies$$1 .movideID);
            parcel$$1 .writeString(movies$$1 .backdropPath);
            parcel$$1 .writeDouble(movies$$1 .stars);
            parcel$$1 .writeString(movies$$1 .title);
            parcel$$1 .writeString(movies$$1 .posterPath);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.example.flixster.models.Movies getParcel() {
        return movies$$0;
    }

    public static com.example.flixster.models.Movies read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.example.flixster.models.Movies movies$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            movies$$4 = new com.example.flixster.models.Movies();
            identityMap$$1 .put(reservation$$0, movies$$4);
            movies$$4 .overview = parcel$$3 .readString();
            movies$$4 .movideID = parcel$$3 .readInt();
            movies$$4 .backdropPath = parcel$$3 .readString();
            movies$$4 .stars = parcel$$3 .readDouble();
            movies$$4 .title = parcel$$3 .readString();
            movies$$4 .posterPath = parcel$$3 .readString();
            com.example.flixster.models.Movies movies$$3 = movies$$4;
            identityMap$$1 .put(identity$$1, movies$$3);
            return movies$$3;
        }
    }

}
