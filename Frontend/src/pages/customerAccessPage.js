import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import GroceryItemClient from "../api/groceryItemClient";

class CustomerAccessPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onGetAll', 'renderGroceryItems'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers
     */
    async mount() {
        document.getElementById('get-by-name-form').addEventListener('submit', this.onGet);
        this.client = new GroceryItemClient();

        this.dataStore.addChangeListener(this.renderGroceryItems)
    }

    // Render Methods --------------------------------------------------------------------------------------------------
    // Updates needed
    async renderGroceryItems() {
        let resultArea = document.getElementById("result-info");

        const groceries = this.dataStore.get("groceries");

        if (groceries) {
            resultArea.innerHTML = `
                <div>Product ID: ${groceries.groceryProductId}</div>
                <div>Product Name: ${groceries.groceryProductName}</div>
                <div>Department: ${groceries.groceryProductDepartment}</div>
                <div>Price: ${groceries.groceryProductPrice}</div>
                <div>Expiration Date: ${groceries.groceryExpirationDate}</div>
                <div>Product Type: ${groceries.groceryType}</div>
                <div>In Stock?: ${groceries.inStock}</div>
                <div>Quantity Available: ${groceries.quantityAvailable}</div>
                <div>Discounted?: ${groceries.discount}</div>
            `
        } else {
            resultArea.innerHTML = "No Grocery Items";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let name = document.getElementById("name-field").value;
          this.dataStore.set("groceries", null);

        let result = await this.client.getGroceryItem(name, this.errorHandler);

          this.dataStore.set("groceries", result);

        if (result) {
            this.showMessage(`Got ${result.groceryProductName}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onGetAll(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("example", null);

        let result = await this.client.getAllGroceryItems(id, this.errorHandler);
        this.dataStore.set("example", result);
        if (result) {
            this.showMessage(`Got ${result.groceryProductName}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */

const main = async () => {
    const customerAccessPage = new CustomerAccessPage();
    customerAccessPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
