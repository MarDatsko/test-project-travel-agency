<html>
    <body>
        <h1>Travel Agency "Sun"</h1>
        <p>We work with such countries, you can see the list of these countries --->
        <a href="/countries">here</a>
        </p>
        <form action="/freeHotel">
            <p>By selecting a country and date, you can see a list of hotels with available rooms for a certain day</p>
            Country: <input type="url" list="url_list" name="link">
            <datalist id="url_list">
                <option label="W3Schools" value="https://www.w3schools.com">
                <option label="Google" value="http://www.google.com">
                <option label="Microsoft" value="http://www.microsoft.com">
            </datalist>
            <label for="birthday">Date:</label>
            <input type="date" id="birthday" name="birthday">
            <input type="submit" value="Find">
        </form>

<div></div>

        <form action="/logout" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
</body>
</html>