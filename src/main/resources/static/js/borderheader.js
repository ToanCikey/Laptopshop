const path = window.location.pathname;
if (path.includes("home")) {
  document
    .getElementById("nav-home")
    ?.classList.add("border-b-2", "border-red-900");
} else if (path.includes("products")) {
  document
    .getElementById("nav-products")
    ?.classList.add("border-b-2", "border-blue-600");
} else if (path.includes("contact")) {
  document
    .getElementById("nav-contact")
    ?.classList.add("border-b-2", "border-blue-600");
}
