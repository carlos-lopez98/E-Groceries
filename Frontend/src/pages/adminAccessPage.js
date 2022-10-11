import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import GroceryItemClient from "../api/groceryItemClient";

class AdminAccessPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onGetAll', 'onCreate', 'onDelete', 'renderGroceryItems'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers
     */
    async mount() {
        document.getElementById('get-by-name-form').addEventListener('submit', this.onGet);
        document.getElementById('get-all-grocery-items-button').addEventListener('click', this.onGetAll);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        document.getElementById('delete-grocery-item-form').addEventListener('submit', this.onDelete);
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
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }


    async onGetAll() {
        let result = await this.client.getAllGroceryItems(this.errorHandler);
        this.dataStore.set("groceries", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("groceries", null);

        let productName = document.getElementById("create-grocery-product-name-field").value;
        let department = document.getElementById("create-grocery-product-department-field").value;
        let price = document.getElementById("create-grocery-product-price-field").value;
        let expirationDate = document.getElementById("create-grocery-product-expiration-date-field").value;
        let type = document.getElementById("create-grocery-product-type-field").value;
        let inStock = document.getElementById("create-grocery-product-in-stock-field").value;
        let quantity = document.getElementById("create-grocery-product-quantity-field").value;
        let discount = document.getElementById("create-grocery-product-discount-field").value;

        const createdGroceryItem = await this.client.createGroceryItem(productName,
            department, price, expirationDate, type, inStock, quantity, discount, this.errorHandler);
        this.dataStore.set("groceries", createdGroceryItem);

        if (createdGroceryItem) {
            this.showMessage(`Created ${createdGroceryItem.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }

    async onDelete(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("groceries", null);

        let name = document.getElementById("delete-name-field").value;

        let deletedItem = await this.client.deleteGroceryItem(name, this.errorHandler);
        this.dataStore.set("groceries", deletedItem);

        if (deletedItem) {
            this.showMessage(`Deleted ${deletedItem.name}!`)
        } else {
            this.errorHandler("Error deleting!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const adminAccessPage = new AdminAccessPage();
    adminAccessPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
