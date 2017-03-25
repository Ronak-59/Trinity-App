package in.djtrinity.www.newapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

//import in.djtrinity.www.trinity.galleryHelper.AppConstant;
//import in.djtrinity.www.trinity.galleryHelper.GridViewImageAdapter;
//import in.djtrinity.www.trinity.galleryHelper.Utils;


public class GalleryFragment extends android.support.v4.app.Fragment {

    /*private Utils utils;
    private boolean cancel_gallery = false;
    private ArrayList<String> imagePaths = new ArrayList<String>();
    private GridViewImageAdapter adapter;
    private GridView gridView;
    private int columnWidth;

    //private FloatingActionButton uploadbutton;
    private static int RESULT_LOAD_IMG = 1;
    private static final int CAMERA_REQUEST = 1888;
    //OutputStream os;
    File selectedfile;

    FTPClient ftpClient = new FTPClient();*/
    public static String mThumbIds[]=new String[SetupActivity.ll.size()];

    public GalleryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_view, container, false);

        Log.d("gallery fragment len ",Integer.toString(mThumbIds.length));
        for(int i=0;i<mThumbIds.length;i++)
        {
            mThumbIds[i]=SetupActivity.ll.get(i).toString();
            Log.d("mthumbsid" ,mThumbIds[i]);
        }

        //setTitle("GridView");
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getContext()));

        // Listening to GridView item click
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // Launch ImageViewPager.java on selecting GridView Item
                Intent i = new Intent(getContext(), ImageViewPager.class);

                // Show a simple toast message for the item position
                //  Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();

                // Send the click position to ImageViewPager.java using intent
                i.putExtra("id", position);

                // Start ImageViewPager
                startActivity(i);
            }
        });
        return view;
    }
}




// gridView = (GridView) view.findViewById(R.id.grid_view);

        /*if (!isNetworkAvailable())
            Toast.makeText(getActivity(), "Internet Required To View The Gallery.", Toast.LENGTH_SHORT).show();
        else
            new GalleryDownload(getActivity(), gridView).execute();

        utils = new Utils(getActivity());
        InitilizeGridLayout();
        imagePaths = utils.getFilePaths();
        adapter = new GridViewImageAdapter(getActivity(), imagePaths,
                columnWidth);
        gridView.setAdapter(adapter);

        //uploadbutton = (FloatingActionButton) view.findViewById(R.id.uploadbutton);
       /* uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customdialog();
                //file uploading code
                try {
//                    ftpClient.connect(InetAddress.getByName("ftp.geocities.ws"), 21);//"http://www.djtrinity.in"));
                    // ftpClient.login("ronak_59", "ronak321");
//c                    Log.e("status :: ", ftpClient.getStatus());
                    FileInputStream srcFileStream = new FileInputStream(selectedfile);
                    boolean status = ftpClient.storeFile("/android_images", srcFileStream);
                    Log.e("Status", String.valueOf(status));
                    srcFileStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });*/


//*********code for upload **************
   /* public void customdialog()
    {

// dialog list entries
        final String[] items = {
                "Camera","Gallery"

        };

// dialog list icons: some examples here
        final int[] icons = {
                R.drawable.camera,
                R.drawable.gallery
        };

        ListAdapter adapter = new ArrayAdapter<String>(
                getActivity(), R.layout.list_item, items) {

            ViewHolder holder;

            class ViewHolder {
                ImageView icon;
                TextView title;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                final LayoutInflater inflater = (LayoutInflater) getActivity()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                if (convertView == null) {
                    convertView = inflater.inflate(
                            R.layout.list_item, null);

                    holder = new ViewHolder();
                    holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    convertView.setTag(holder);
                } else {
                    // view already defined, retrieve view holder
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.title.setText(items[position]);

                holder.icon.setImageResource(icons[position]);
                return convertView;
            }
        };



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select");

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {

                    //if camera is selected
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                if (which == 1) {
                    //if gallery is selected
                    loadImagefromGallery();
                }

            }

        });

        builder.create();
        if (! ((Activity) getActivity()).isFinishing()) {
            builder.show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");


                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getActivity(), photo);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                // File finalFile = new File(getRealPathFromURI(tempUri));
                String path=getRealPathFromURI(tempUri);

                selectedfile=saveBitmap(photo, path);


                //imgView.setImageBitmap(photo);
            }

            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == Activity.RESULT_OK && null != data) {
                ContentResolver resolver = getActivity().getContentResolver();
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, imageUri);


            }
        }
        catch(Exception e)
        {
            System.err.print(e);

        }
    }
    //--------------------------------------------------------------
    //*****************select a pic from gallery**********************

    public void loadImagefromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    private File saveBitmap(Bitmap bitmap, String path) {
        File file = null;
        if (bitmap != null) {
            file = new File(path);
            try {
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(path); //here is set your file path where you want to save or also here you can set file object directly

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // bitmap is your Bitmap instance, if you want to compress it you can compress reduce percentage
                    // PNG is a lossless format, the compression factor (100) is ignored
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

*/

//********************

/*
    class GalleryDownload extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        Context c;
        GridView gridView;

        GalleryDownload(Context c, GridView gridView) {
            this.c = c;
            this.gridView = gridView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(c);
            pd.setMessage("Loading...");
            pd.setCancelable(true);
            pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancel_gallery = true;
                }
            });
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                FTPClient ftpClient = new FTPClient();
                ftpClient.connect(InetAddress.getByName("ftp.geocities.ws"), 21);//"http://www.djtrinity.in"));
                ftpClient.login("ronak_59", "ronak321");
                Log.e("status :: ", ftpClient.getStatus());
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                try {
                    FTPFile[] files = ftpClient.listFiles("/android_images");
                    File galleryDir = new File(getActivity().getExternalFilesDir(null).toString() + "/gallery");
                    if (!galleryDir.exists())
                        galleryDir.mkdir();
                    ArrayList<String> sv_files=new ArrayList<>();

                    for (FTPFile filename : files) {
                        OutputStream out = null;
                        try {
                            sv_files.add(filename.getName());
                            File outFile = new File(galleryDir, filename.getName());
                            if (!outFile.exists()) {
                                out = new BufferedOutputStream(new FileOutputStream(outFile));
                                if (ftpClient.retrieveFile("/android_images/" + filename.getName(), out))
                                if (out != null)
                                    out.close();
                            }
                        } catch (Exception e) {
                        }
                        if (cancel_gallery)
                            break;
                    }
                    if(!cancel_gallery){
                        File f[]=galleryDir.listFiles();
                        for(File t:f){
                            if(!sv_files.contains(t.getName())){
                                t.delete();
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            imagePaths = utils.getFilePaths();
            GridViewImageAdapter adapter = new GridViewImageAdapter(getActivity(), imagePaths,
                    columnWidth);
            gridView.setAdapter(adapter);
        }

        //gallery load method
        private void copyFile(InputStream in, OutputStream out) throws IOException {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
    }

    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AppConstant.GRID_PADDING, r.getDisplayMetrics());

        columnWidth = (int) ((utils.getScreenWidth() - (2 * padding)) / AppConstant.NUM_OF_COLUMNS);

        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding(0, (int) padding, 0, (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
*/