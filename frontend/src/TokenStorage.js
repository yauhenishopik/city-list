import secureLocalStorage from "react-secure-storage";

export function setToken(token) {
    secureLocalStorage.setItem('token', 'Basic ' + token)
}
export function getToken() {
    return secureLocalStorage.getItem('token')
}

export function removeToken() {
    secureLocalStorage.removeItem('token')
}

export function hasRole(role) {
    return getUserRoles().includes(role)
}

export function getUserRoles() {
    let username = atob(secureLocalStorage.getItem('token').substring(6)).split(":")[0];
    if (username === "edit_user") {
        return ["ROLE_ALLOW_EDIT"]
    }
    return [];
}