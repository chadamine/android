package chadamine.com.databaselist.Business.Products;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import chadamine.com.databaselist.R;

public class ProductsActivity extends ActionBarActivity {

    private final String PRODUCTS_FRAGMENT_TAG = "productsFragmentTag";
    private ProductsFragment mProductsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        if (savedInstanceState != null) {
            mProductsFragment = (ProductsFragment) getSupportFragmentManager()
                    .findFragmentByTag(PRODUCTS_FRAGMENT_TAG);
        } else {
            if(mProductsFragment == null) {
                mProductsFragment = new ProductsFragment();
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_product_activity, new ProductsFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_products_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
