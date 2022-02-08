# UAS - DOKUMENTASI POWERCOIL

## DESKRIPSI

Sistem Monitoring Pembangkit Listrik Energi Baru Terbarukan (Power Coil) adalah  aplikasi untuk memantau pembangkit listrik tenaga angin dan pembangkit listrik tenaga bayu. Data di ambil dari JSON yang di di aplikasikan dengan rest api pada oracle cloud kemudian di fetch untuk ditampilkan pada aplikasi. Power Coil memungkinkan melihat notifikasi pembangkit listrik yang tidak normal dan alert warning pembangkit listrik yang sedang mengalami kendala. 

## Fitur :
1. Multiple Login Admin, Operator, Konsultan & Pekerja Lapangan.
2. Beranda 
- Tahap 1 Pengkajian :Pada tahap pengkajian konsultan melakukan input data pada aplikasi PowerCoil lalu pekerja proyek akan men-survey tempat yang akan dijadikan pembangkit listrik.
- Tahap 2 Surat Perintah Kerja (SPK) :  Pada tahap SPK, setelah proses survey selesai dan proses pengkajian selesai maka Konsultan Proyek akan membuat SPK untuk pekerja lapangan. Konsultan Proyek dapat mengupload SPK pada aplikasi ini, dan pekerja proyek dapat mendownload SPK.
- Tahap 3 Pemasangan : Pada tahap pemasangan pekerja proyek datang ke lokasi yang telah disetujui dengan membawa SPK. Pada proses pemasangan pekerja proyek diharuskan mengisi data data yang ada pada tahap pemasangan di aplikasi ini.
- Tahap 4 Operasi : Pada tahap Operasi berisi status tiap titik pembangkit, terdapat alert pada setiap warna, merah = darurat, kuning = peringatan, hijau = aman.
3. Push Notifikasi dengan firebase yang dijadwal kan pagi dan sore sebagai pengingat admin/operator & Notifikasi terdapat daftar pembangkit listrik yang sedang mengalami masalah.
4. CRUD user berisi informasi list pengguna aplikasi
5. Faq & Panduan Aplikasi
6. Logout



## Hasil 

1. Halaman Splash Screen

![Screenshot Dashboard Oracle](img/Screenshot_1.png)


2. Halaman Login(Use SQLite)

![Screenshot Dashboard Oracle](img/Screenshot_2.png)


### Admin

1. Halaman Home Admin

![Screenshot Dashboard Oracle](img/Screenshot_26.png)


2. Halaman Side Navigation

![Screenshot Dashboard Oracle](img/Screenshot_27.png)


3. Halaman Notification

![Screenshot Dashboard Oracle](img/Screenshot_28.png)


4. Halaman Operasi Google Maps

![Screenshot Dashboard Oracle](img/Screenshot_29.png)


5. Halaman Warning Alert Monitoring

![Screenshot Dashboard Oracle](img/Screenshot_7.png)


6. Halaman List Pembangkit Listrik

![Screenshot Dashboard Oracle](img/Screenshot_30.png)


7. Halaman Detail Pembangkit Listrik

![Screenshot Dashboard Oracle](img/Screenshot_31.png)
![Screenshot Dashboard Oracle](img/Screenshot_32.png)


8. Halaman Edit Pembangkit Listrik dan Hapus Pembangkit Listrik

![Screenshot Dashboard Oracle](img/Screenshot_33.png)


9. Halaman Tambah Pembangkit Listrik.

![Screenshot Dashboard Oracle](img/Screenshot_34.png)
![Screenshot Dashboard Oracle](img/Screenshot_35.png)
![Screenshot Dashboard Oracle](img/Screenshot_36.png)
![Screenshot Dashboard Oracle](img/Screenshot_37.png)


10. Listview User 

![Screenshot Dashboard Oracle](img/Screenshot_38.png)


11. Tambah User

![Screenshot Dashboard Oracle](img/Screenshot_39.png)
![Screenshot Dashboard Oracle](img/Screenshot_40.png)


12. Edit User

![Screenshot Dashboard Oracle](img/Screenshot_41.png)
![Screenshot Dashboard Oracle](img/Screenshot_42.png)
![Screenshot Dashboard Oracle](img/Screenshot_43.png)


13. Delete User

![Screenshot Dashboard Oracle](img/Screenshot_44.png)
![Screenshot Dashboard Oracle](img/Screenshot_45.png)


14. List Pengkajian 

![Screenshot Dashboard Oracle](img/Screenshot_46.png)


14. Tambah Pengkajian 

![Screenshot Dashboard Oracle](img/Screenshot_47.png)
![Screenshot Dashboard Oracle](img/Screenshot_48.png)


15. Detail Pengkajian

![Screenshot Dashboard Oracle](img/Screenshot_49.png)


16. Download SPK

![Screenshot Dashboard Oracle](img/Screenshot_50.png)


16. Edit Pengkajian

![Screenshot Dashboard Oracle](img/Screenshot_51.png)
![Screenshot Dashboard Oracle](img/Screenshot_52.png)


16. Delete Pengkajian

![Screenshot Dashboard Oracle](img/Screenshot_53.png)
![Screenshot Dashboard Oracle](img/Screenshot_54.png)


17. Halaman Panduan

![Screenshot Dashboard Oracle](img/Screenshot_55.png)


18.  Halaman Faq

![Screenshot Dashboard Oracle](img/Screenshot_56.png)

### Konsultan

1.Login sebagai konsultan

![Screenshot Dashboard Oracle](img/Screenshot_57.png)


2.Home konsultan

![Screenshot Dashboard Oracle](img/Screenshot_58.png)


3. Fitur Pengkajian dapat di akses hak akses penuh oleh Konsultan

![Screenshot Dashboard Oracle](img/Screenshot_59.png)


4. Fitur User pada konsultan hanya bisa CRUD sebagai Pekerja Lapangan.

![Screenshot Dashboard Oracle](img/Screenshot_60.png)
![Screenshot Dashboard Oracle](img/Screenshot_61.png)
![Screenshot Dashboard Oracle](img/Screenshot_62.png)


### Pekerja Lapangan


1.Login sebagai Pekerja Lapangan

![Screenshot Dashboard Oracle](img/Screenshot_64.png)


2. Home Pekerja Lapangan

![Screenshot Dashboard Oracle](img/Screenshot_64.png)


3. Pekerja Lapangan dapat hak akses penuh untuk CRUD pembangkit listrik

![Screenshot Dashboard Oracle](img/Screenshot_65.png)


4. Pekerja Lapangan hanya dapat read pengkajian.

![Screenshot Dashboard Oracle](img/Screenshot_66.png)

### Operator

1. Login Sebagai operator

![Screenshot Dashboard Oracle](img/Screenshot_67.png)


2. Home Operator

![Screenshot Dashboard Oracle](img/Screenshot_68.png)


3. Operator Lapangan dapat hak akses penuh untuk CRUD pembangkit listrik

![Screenshot Dashboard Oracle](img/Screenshot_65.png)


4. Operator dapat monitoring pembangkit listrik

![Screenshot Dashboard Oracle](img/Screenshot_69.png)


5. Operator dapat notification

![Screenshot Dashboard Oracle](img/Screenshot_70.png)


6. Operator dapat memantau pembangkit listrik melalui MAPS

![Screenshot Dashboard Oracle](img/Screenshot_71.png)