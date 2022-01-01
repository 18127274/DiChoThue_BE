using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
// <snippet_NewtonsoftJsonImport>
using Newtonsoft.Json;
// </snippet_NewtonsoftJsonImport>

namespace API_PTUD_R5.Model
{
    public class KHACHHANG
    {
        [BsonId]

        public string Id { get; set; }

        public string Tendangnhap { get; set; }

        public string Matkhau { get; set; }

        public string Hoten { get; set; }

        public string Sdt { get; set; }

        public string Email { get; set; }

        public string Diachi { get; set; }

        public string Mavung { get; set; }
    }
}
