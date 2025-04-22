const avatar = document.getElementById("avatar-container");
const dropdown = document.getElementById("dropdown-menu");

avatar?.addEventListener("click", () => {
  dropdown.classList.toggle("hidden");
});

window.addEventListener("click", function (e) {
  if (!document.getElementById("avatar-wrapper")?.contains(e.target)) {
    dropdown?.classList.add("hidden");
  }
});
