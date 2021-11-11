// let sessionName= '<%= Session["UserName"] %>';
// console.log(sessionName);
// sessionName=sessionStorage.getItem('user');
// console.log(sessionName);



const logoutButton = document.getElementsByClassName("logoutbutton").item(0);
if (logoutButton != null) {
    logoutButton.addEventListener("click", () => {
        window.location.href = "logout.php";
    });
}

const backtodataviewButton = document.getElementsByClassName("databutton").item(0);
if (backtodataviewButton != null) {
    backtodataviewButton.addEventListener("click", () => {
        window.location.href = "data.php";
    });
}

const registerButton = document.getElementsByClassName("registerbutton").item(0);
if (registerButton != null) {
    registerButton.addEventListener("click", () => {
        window.location.href = "register.php";
    });
}

const loginButton = document.getElementsByClassName("loginbutton").item(0)
if (loginButton != null) {
    loginButton.addEventListener("click", () => {
        window.location.href = "index.php";
    });
}

const addForm = document.getElementById("addform");

// const toggleButton = document.getElementsByTagName("toggle").item(0);
// if (toggleButton != null) {
//     toggleButton.addEventListener("click", () => {
//         addForm.classList.toggle("hidden");
//         console.log("toggle");
//     });
// }


const deleteButtons = document.getElementsByClassName("deletebutton");
const editButtons = document.getElementsByClassName("editbutton");


const newCustomerButton = document.getElementById("newcustomerbutton");
console.log(newCustomerButton);
if (newCustomerButton != null) {
    console.log("Test");
    newCustomerButton.addEventListener("click", () => {
        console.log("Test2");
        window.location.href = "insert.php";
    });
}