SELECT u.userID
FROM Users u, PrivilegedUser pu
WHERE u.userID = pu.userID AND pu.adminLevel = 1;
