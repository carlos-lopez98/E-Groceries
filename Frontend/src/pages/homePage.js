import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import GroceryItemClient from "../api/groceryItemClient";

class HomePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'renderGroceryItems'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers
     */
    async mount() {
        document.getElementById('get-by-name-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
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
                <div>ID: ${groceries.id}</div>
                <div>Name: ${groceries.name}</div>
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
        this.dataStore.set("grocery items", result);

        let result = await this.client.getGroceryItem(name, this.errorHandler);
        this.dataStore.set("grocery items", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    // async onGetAll(event) {
    //     // Prevent the page from refreshing on form submit
    //     event.preventDefault();
    //
    //     let id = document.getElementById("id-field").value;
    //     this.dataStore.set("example", null);
    //
    //     let result = await this.client.getExample(id, this.errorHandler);
    //     this.dataStore.set("example", result);
    //     if (result) {
    //         this.showMessage(`Got ${result.name}!`)
    //     } else {
    //         this.errorHandler("Error doing GET!  Try again...");
    //     }
    // }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let productName = document.getElementById("create-grocery-product-name").value;
        let department = document.getElementById("create-grocery-product-department").value;
        let price = document.getElementById("create-grocery-product-price").value;
        let expirationDate = document.getElementById("create-grocery-product-expiration-date").value;
        let type = document.getElementById("create-grocery-product-type").value;
        let inStock = document.getElementById("create-grocery-product-in-stock").value;
        let quantity = document.getElementById("create-grocery-product-quantity").value;
        let discount = document.getElementById("create-grocery-product-discount").value;

        const createdGroceryItem = await this.client.createdGroceryItem(productName,
         department, price, expirationDate, type, inStock, quantity, discount, this.errorHandler);

        if (createdGroceryItem) {
            this.showMessage(`Created a new grocery item!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }

    // async onDelete(event) {
    //     // Prevent the page from refreshing on form submit
    //     event.preventDefault();
    //     this.dataStore.set("example", null);
    //
    //     let name = document.getElementById("create-name-field").value;
    //
    //     const createdExample = await this.client.createExample(name, this.errorHandler);
    //     this.dataStore.set("example", createdExample);
    //
    //     if (createdExample) {
    //         this.showMessage(`Created ${createdExample.name}!`)
    //     } else {
    //         this.errorHandler("Error creating!  Try again...");
    //     }
    // }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const groceryItemPage = new HomePage();
    groceryItemPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
