import {
  Text,
  Code,
  Icon,
  List,
  ListIcon,
  ListItem,
  Box,
  Image,
  Flex,
  Heading,
  Link,
  IconButton,
} from "@chakra-ui/core";
import axios from "axios";
import { AiOutlineHeart, AiOutlineShopping } from "react-icons/ai";
import { BsPerson } from "react-icons/bs";
import { HiOutlineShoppingCart } from "react-icons/hi";
import { useQuery } from "react-query";
import { Item } from "../../types";
import { ItemPreviewCard } from "../components/ItemPreviewCard";
import { Navbar } from "../components/Navbar";

const Index = () => {
  const { data: itemData, isLoading, error, isError } = useQuery(
    "fetchItems",
    async () => {
      return await axios.get("http://localhost:8080/api/items");
    }
  );

  const { data: meData } = useQuery("fetchMe", async () => {
    return await axios.get("http://localhost:8080/api/users/me");
  });

  const itemBody = [];

  if (isLoading) {
    return <p>...Loading</p>;
  }

  if (isError) {
    return (
      <>
        <p>There was an error loading items, please try again</p>
        <p>{error}</p>
      </>
    );
  }

  return (
    <>
      <Navbar />
      <Box ml={15} textAlign="center">
        <Heading>Welcome to The Cushion!</Heading>
        <Text>The world's greatest fake furntiure store</Text>
        <Text>Browse fake couches, chairs, tables and more!</Text>
        <br />
        <Text>
          View items by clicking the
          <IconButton
            icon={AiOutlineShopping}
            fontSize={20}
            aria-label="Shop"
            variant="outline"
          />{" "}
          icon in the navigation bar.
        </Text>
        {!meData?.data.success ? (
          <Text>
            You can also view the contents of your personal wishlist by clicking
            the{" "}
            <IconButton
              icon={AiOutlineHeart}
              fontSize={20}
              aria-label="Wishlist"
              variant="outline"
            />{" "}
            icon or your shopping cart using the{" "}
            <IconButton
              icon={HiOutlineShoppingCart}
              fontSize={20}
              aria-label="Cart"
              variant="outline"
            />{" "}
            icon.
          </Text>
        ) : (
          <Text>
            Log in using the <Icon as={BsPerson} /> to add items to your
            personal wishlist, shopping cart or to pretend to buy any of the
            items in our store!
          </Text>
        )}
      </Box>
      <Flex m={25} flexDir="row" justifyContent="center">
        {itemData.data.reduce((itemArr, item, i) => {
          if (i < 10 && i < itemData.data.length) {
            itemArr.push(<ItemPreviewCard item={item} />);
          }
          return itemArr;
        }, [])}
      </Flex>
    </>
  );
};

export default Index;
